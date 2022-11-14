package sign_up.use_case;

import entities.User;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.After;
import java.util.ArrayList;

/*
* Define a test class for the sign_up use case. It will test all methods of the class for correctness.
*
* */
public class SignUpTest {
    // The testing interactor
    private static SignUpInteractor signUpInteractor;
    // The testing presenter
    private static DummyOutput dummyOutput;
    // The testing database
    private static DataList dummyData;

    /*
    * Define a dummy class which implements SignUpDataAccessInterface
    * */
    private static class DataList implements SignUpDataAccessInterface {
        ArrayList<User> data = new ArrayList<>();
        String adminPassword = "123";
        int numUsers = 0;

        // Implementations of each method for SignUpDataAccessInterface
        public int getNumberUsers() {
            return numUsers;
        }

        public void setNumberUsers(int numUsers) {
            this.numUsers = numUsers;
        }

        public boolean checkUserEmailExists(String email) {
            for (User user: data) {
                if (user.getEmail().equals(email)) {
                    return true;
                }
            }
            return false;
        }

        public void saveUser(Object toSave, String type) {
            data.add((User) toSave);
        }

        public String getAdminPassword() {
            return this.adminPassword;
        }

    }

    /*
    * Dummy implementation of SignUpOutputBoundary
    * */
    private static class DummyOutput implements SignUpOutputBoundary {
        public SignUpResponseModel responseModel;

        public void present(SignUpResponseModel responseModel) {
            this.responseModel = responseModel;
        }
    }

    /*
    * instantiate a dummy DataList for data access, SignUpRequestModel, and dummy SignUpOutputBoundary
    * */
    @Before
    public void init() {
        dummyData = new DataList();
        dummyOutput = new DummyOutput();

        signUpInteractor = new SignUpInteractor(dummyData, dummyOutput);
    }

    @After
    public void teardown(){}

    @Test
    public void testSignUpEmailExistsError() {
        dummyData.saveUser(new User(true, 0, "email", "password"), "");

        SignUpRequestModel signUpRequestModel = new SignUpRequestModel("email", "password",
                true, "123");

        signUpInteractor.signUp(signUpRequestModel);

        Assertions.assertTrue(!dummyOutput.responseModel.creationSuccess &&
                dummyOutput.responseModel.message.equals("email"));
    }

    @Test
    public void testSignUpNoAdminSuccess() {
        SignUpRequestModel signUpRequestModel = new SignUpRequestModel("email", "password",
                false, "");

        signUpInteractor.signUp(signUpRequestModel);

        Assertions.assertTrue(dummyOutput.responseModel.creationSuccess &&
                dummyOutput.responseModel.message.equals(""));
    }

    @Test
    public void testSignUpAdminFailure() {
        SignUpRequestModel signUpRequestModel = new SignUpRequestModel("email", "password",
                true, "122");

        signUpInteractor.signUp(signUpRequestModel);

        Assertions.assertTrue(!dummyOutput.responseModel.creationSuccess &&
                dummyOutput.responseModel.message.equals("admin password"));
    }

    @Test
    public void testSignUpAdminSuccess() {
        SignUpRequestModel signUpRequestModel = new SignUpRequestModel("email", "password",
                true, "123");

        signUpInteractor.signUp(signUpRequestModel);

        Assertions.assertTrue(dummyOutput.responseModel.creationSuccess &&
                dummyOutput.responseModel.message.equals(""));
    }
}
