package log_in;


import entities.User;
import favourite.use_case.UserFactory;
import log_in.interface_adapters.*;

import static jdk.dynalink.linker.support.Guards.isNotNull;
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
    UserFactory userFactory;


    // need to test private files
    @Before
    public void logIn(){
        repository = new LogInDsGateway() {
            public final List<User> users = new ArrayList<>();

            // method for testing purposes will update when csv is up
            public void addUser(User user){
                users.add(user);
            }

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

            public String[] getUser(boolean success, String email, String pass) {
                String[] userInfo = new String[6];

                if (success){
                    ArrayList<String> ids = this.getData(0);
                    ArrayList<String> emails = this.getData(2);
                    ArrayList<String> admins = this.getData(1);
                    ArrayList<String> posts = this.getData(4);
                    ArrayList<String> favs = this.getData(5);

                    int emailIndex = emails.indexOf(email);
                    String adminValueString = admins.get(emailIndex);

                    userInfo[0] = ids.get(emailIndex);
                    userInfo[1] = adminValueString;
                    userInfo[2] = email;
                    userInfo[3] = pass;
                    if (!posts.isEmpty()) {
                        userInfo[4] = posts.get(emailIndex);
                    } else {
                        userInfo[4] = "";
                    }
                    if (!favs.isEmpty()) {
                        userInfo[5] = favs.get(emailIndex);
                    } else {
                        userInfo[5] = "";
                    }

                } else {
                    return null;
                }

                return new User(Boolean.getBoolean(userInfo.get(1)), 0, userInfo.get(0), "");
            }

            public ArrayList<String> getData(int index){
                ArrayList<String> userInfo = new ArrayList<>();
                for (User i: users){
                    switch (index) {
                        case 0:
                            userInfo.add(String.valueOf(i.getId()));
                            break;
                        case 1:
                            String isAdminString;
                            if (i.isAdmin()){
                                isAdminString = "True";
                            } else {
                                isAdminString = "False";
                            }
                            userInfo.add(isAdminString);
                            break;
                        case 2:
                            userInfo.add(i.getEmail());
                            break;
                        case 3:
                            userInfo.add(i.getPassword());
                            break;
                        case 4:
                            StringBuilder postIds = new StringBuilder();
                            List<Integer> replies = i.getPosts();
                            for (int id : replies) {
                                if (replies.indexOf(id) == 0) {
                                    postIds = new StringBuilder(Integer.toString(id));
                                } else {
                                    String idString = Integer.toString(id);
                                    postIds.append(" ").append(idString);
                                }
                            }
                            userInfo.add(postIds.toString());
                            break;
                        case 5:
                            StringBuilder favIds = new StringBuilder();
                            List<Integer> replies1 = i.getPosts();
                            for (int id : replies1) {
                                if (replies1.indexOf(id) == 0) {
                                    favIds = new StringBuilder(Integer.toString(id));
                                } else {
                                    String idString = Integer.toString(id);
                                    favIds.append(" ").append(idString);
                                }
                            }
                            break;
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
        userFactory = new UserFactory();
        interactor = new LogInInteractor(repository, presenter, userFactory);
        User user = new User(false, 0, "a", "b");
        repository.addUser(user);
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
        userFactory = new UserFactory();
        interactor = new LogInInteractor(repository, presenter, userFactory);
        User user = new User(false, 0, "a", "b");
        repository.addUser(user);
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
        userFactory = new UserFactory();
        interactor = new LogInInteractor(repository, presenter, userFactory);
        User user = new User(false, 0, "a", "b");
        repository.addUser(user);
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
        userFactory = new UserFactory();
        interactor = new LogInInteractor(repository, presenter, userFactory);
        User user = new User(false, 0, "a", "b");
        repository.addUser(user);
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
        userFactory = new UserFactory();
        interactor = new LogInInteractor(repository, presenter, userFactory);
        User user = new User(false, 0, "a", "b");
        repository.addUser(user);
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
        userFactory = new UserFactory();
        viewModel.addObserver(observer);
        interactor = new LogInInteractor(repository, presenter, userFactory);
        User user = new User(false, 0, "a", "b");
        repository.addUser(user);
        controller = new LogInController(interactor);

        LogInControllerData test = new LogInControllerData("a", "b");
        controller.logInInitializer(test);
    }
}
