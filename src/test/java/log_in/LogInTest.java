package log_in;


import entities.User;
import log_in.interface_adapters.*;

import static org.junit.Assert.*;

import log_in.use_case.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class LogInTest {
    LogInDsGateway repository;
    LogInInputBoundary interactor;
    LogInOutputBoundary presenter;
    LogInController controller;



    public final List<User> users = new ArrayList<>();

    // method for testing purposes will update when csv is up
    public void addUser(User user){
        users.add(user);
    }

    @Before
    public void logIn(){
        repository = new LogInDsGateway() {

            @Override
            public boolean checkUserEmailExists(String email) {
                for (int i = 0; i <= users.size(); i++){
                    User user = users.get(i);
                    if (user.getEmail().equals(email)){
                        return true;
                    } else{
                        i++;
                    }
                }
                return false;
            }

            @Override
            public boolean checkPasswordMatches(String email, String pass) {
                for (int i = 0; i <= users.size(); i++){
                    User user = users.get(i);
                    if (user.getEmail().equals(email) && user.getPassword().equals(pass)){
                        return true;
                    } else{
                        i++;
                    }
                }
                return false;

            }

            @Override
            public ArrayList<String> getUser(boolean success, String email, String pass) {
                ArrayList<String> userInfo = new ArrayList<>();
                if (success){
                    ArrayList<String> emails = this.getData(4);
                    ArrayList<String> admins = this.getData(2);

                    int emailIndex = emails.indexOf(email);
                    String adminValueString = admins.get(emailIndex);
                    userInfo.add(email);
                    userInfo.add(pass);
                    userInfo.add(adminValueString);
                } else {
                    return null;
                }

                return userInfo;
            }

            public ArrayList<String> getData(int index){
                ArrayList<String> userInfo = new ArrayList<>();
                for (User i: users){
                    if (index == 2){
                        boolean isAdmin = i.isAdmin();
                        String isAdminString;
                        if (isAdmin){
                            isAdminString = "True";
                        } else {
                            isAdminString = "False";
                        }
                        userInfo.add(isAdminString);
                    } if (index == 4) {
                        userInfo.add(i.getEmail());
                    }
                }
                return userInfo;
            }
        };
    }

    @After
    public void teardown(){}

    @Test
    public void logInSuccess(){
        presenter = new LogInOutputBoundary() {
            @Override
            public void present(LogInResponseModel responseModel) {
                boolean creation = responseModel.getLogInSuccess();
                String actual = responseModel.getErrorMessage();

                assertTrue(creation);
                assertEquals("", actual);

            }
        };

        interactor = new LogInInteractor(repository, presenter);
        User user = new User(false, 0, "a", "b");
        this.addUser(user);
        controller = new LogInController(interactor);
        LogInControllerData test = new LogInControllerData("a", "b");

        controller.logInInitializer(test);
    }

    @Test
    public void logInFailEmptyEmail(){
        presenter = new LogInOutputBoundary() {
            @Override
            public void present(LogInResponseModel responseModel) {
                boolean creation = responseModel.getLogInSuccess();
                String actual = responseModel.getErrorMessage();

                assertFalse(creation);
                assertEquals("Empty Email or Password", actual);

            }
        };
        interactor = new LogInInteractor(repository, presenter);
        User user = new User(false, 0, "a", "b");
        this.addUser(user);
        controller = new LogInController(interactor);
        LogInControllerData test = new LogInControllerData("", "b");

        controller.logInInitializer(test);
    }

    @Test
    public void logInFailEmptyPass(){
        presenter = new LogInOutputBoundary() {
            @Override
            public void present(LogInResponseModel responseModel) {
                boolean creation = responseModel.getLogInSuccess();
                String actual = responseModel.getErrorMessage();

                assertFalse(creation);
                assertEquals("Empty Email or Password", actual);

            }
        };
        interactor = new LogInInteractor(repository, presenter);
        User user = new User(false, 0, "a", "b");
        this.addUser(user);
        controller = new LogInController(interactor);
        LogInControllerData test = new LogInControllerData("a", "");

        controller.logInInitializer(test);
    }

    @Test
    public void logInIncorrectEmail(){
        presenter = new LogInOutputBoundary() {
            @Override
            public void present(LogInResponseModel responseModel) {
                boolean creation = responseModel.getLogInSuccess();
                String actual = responseModel.getErrorMessage();

                assertFalse(creation);
                assertEquals("Incorrect Email", actual);

            }
        };
        interactor = new LogInInteractor(repository, presenter);
        User user = new User(false, 0, "a", "b");
        this.addUser(user);
        controller = new LogInController(interactor);
        LogInControllerData test = new LogInControllerData("b", "a");

        controller.logInInitializer(test);
    }

    @Test
    public void logInIncorrectPass(){
        presenter = new LogInOutputBoundary() {
            @Override
            public void present(LogInResponseModel responseModel) {
                boolean creation = responseModel.getLogInSuccess();
                String actual = responseModel.getErrorMessage();

                assertFalse(creation);
                assertEquals("Incorrect Password", actual);

            }
        };
        interactor = new LogInInteractor(repository, presenter);
        User user = new User(false, 0, "a", "b");
        this.addUser(user);
        controller = new LogInController(interactor);
        LogInControllerData test = new LogInControllerData("a", "c");

        controller.logInInitializer(test);
    }

    @Test
    public void logInObserver(){
        LogInViewModel viewModel = new LogInViewModel();
        presenter = new LogInPresenter(viewModel);
        PropertyChangeListener observer = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("Login Success")){
                    assertTrue((boolean) evt.getNewValue());
                }
            }
        };

        viewModel.addObserver(observer);
        interactor = new LogInInteractor(repository, presenter);
        User user = new User(false, 0, "a", "b");
        this.addUser(user);
        controller = new LogInController(interactor);

        LogInControllerData test = new LogInControllerData("a", "b");
        controller.logInInitializer(test);
    }
}
