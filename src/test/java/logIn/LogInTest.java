package logIn;

import entities.User;
import login.use_case.LogInInteractor;
import login.use_case.LogInOutputBoundary;
import login.use_case.LogInDsGateway;
import login.use_case.LogInResponseModel;
import login.use_case.LogInRequestModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

public class LogInTest {
    private static LogInInteractor logInInteractor;

    private static DummyOutput dummyOutput;

    private static DataList dummyUser;

    private static class DataList implements LogInDsGateway {

        ArrayList<User> data = new ArrayList<>();

        public boolean checkUserEmailExists(String email) {
            for (User user: data)  {
                if (user.getEmail().equals(email)){
                    return true;
                }
            }
            return false;
        }
        public boolean checkPasswordMatches(String email, String pass) {
            for (User user : data) {
                if (user.getEmail().equals(email)) {
                    return user.getPassword().equals(pass);
                }
            }
            return false;
        }

        @Override
        public User getUser() {
            return null;
        }

        //testing method to add a user to test inputs
        public void add(Object user){
            data.add((User) user);
        }
    }

    private static class DummyOutput implements LogInOutputBoundary {
        public LogInResponseModel responseModel;

        public void present(LogInResponseModel responseModel){
            this.responseModel = responseModel;
        }
    }

    @Before
    public void init(){
        dummyUser = new DataList();
        dummyOutput = new DummyOutput();
        logInInteractor = new LogInInteractor(dummyUser, dummyOutput);
    }

    @After
    public void teardown(){}

    @Test
    public void testLogInSuccess(){
        dummyUser.add(new User(false, 0, "email", "pass"));

        LogInRequestModel logInRequestModel = new LogInRequestModel("email", "pass");

        logInInteractor.logIn(logInRequestModel);

        boolean success = dummyOutput.responseModel.getLogInSuccess();
        String message = dummyOutput.responseModel.getErrorMessage();

        assert success && Objects.equals(message, "");

    }

    @Test
    public void testLogInEmailIncorrectError(){
        dummyUser.add(new User(false, 0, "1email", "1pass"));

        LogInRequestModel  logInRequestModel = new LogInRequestModel("email", "pass");

        logInInteractor.logIn(logInRequestModel);

        boolean success = dummyOutput.responseModel.getLogInSuccess();
        String message = dummyOutput.responseModel.getErrorMessage();

        assert !success && Objects.equals(message, "Incorrect Email");
    }

    @Test
    public void testLogInPassIncorrectError(){
        dummyUser.add(new User(false, 0, "email", "1pass"));

        LogInRequestModel  logInRequestModel = new LogInRequestModel("email", "pass");

        logInInteractor.logIn(logInRequestModel);

        boolean success = dummyOutput.responseModel.getLogInSuccess();
        String message = dummyOutput.responseModel.getErrorMessage();

        assert !success && Objects.equals(message, "Incorrect Password");
    }

    @Test
    public void testLogInEmailEmptyError(){
        dummyUser.add(new User(false, 0, "email", "1pass"));

        LogInRequestModel  logInRequestModel = new LogInRequestModel("", "pass");

        logInInteractor.logIn(logInRequestModel);

        boolean success = dummyOutput.responseModel.getLogInSuccess();
        String message = dummyOutput.responseModel.getErrorMessage();

        assert !success && Objects.equals(message, "Empty Email or Password");
    }

    @Test
    public void testLogInPassEmptyError(){
        dummyUser.add(new User(false, 0, "email", "1pass"));

        LogInRequestModel  logInRequestModel = new LogInRequestModel("email", "");

        logInInteractor.logIn(logInRequestModel);

        boolean success = dummyOutput.responseModel.getLogInSuccess();
        String message = dummyOutput.responseModel.getErrorMessage();

        assert !success && Objects.equals(message, "Empty Email or Password");
    }
}
