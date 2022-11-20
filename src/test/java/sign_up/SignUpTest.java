package sign_up;

import entities.User;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import sign_up.interface_adapters.SignUpController;
import sign_up.interface_adapters.SignUpPresenter;
import sign_up.interface_adapters.SignUpUserInputData;
import sign_up.interface_adapters.SignUpViewModel;
import sign_up.use_case.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


/*
* Test which will test for correctness of the sign up use case
*
*
* */
public class SignUpTest {
    SignUpDSGateway postRepository;
    SignUpOutputBoundary presenter;
    SignUpInputBoundary interactor;
    SignUpController controller;

    @Before
    public void setup() {
        // Create an anonymous class to mimi the data access

        postRepository = new SignUpDSGateway() {
            private int numPosts;
            public final List<User> users = new ArrayList<User>();


            @Override
            public int getNumberUsers() {
                return numPosts;
            }

            @Override
            public void setNumberUsers(int numberUsers) {
                this.numPosts = numberUsers;
            }

            @Override
            public List<String> getEmails() {
                List<String> emails = new ArrayList<String>();
                for (User user: users) {
                    emails.add(user.getEmail());
                }
                return emails;
            }

            @Override
            public void saveUser(User toSave) {
                this.users.add(toSave);
            }

            @Override
            public String getAdminPassword() {
                return "123";
            }
        };
    }

    @After
    public void teardown(){}

    @Test
    public void testSignUpNoAdminSuccess() {
        presenter = new SignUpOutputBoundary() {
            @Override
            public void updateViewModel(SignUpResponseModel responseModel) {
                boolean creation = responseModel.isCreationSuccess();
                String actual = responseModel.getMessage();

                assertTrue(creation);
                assertEquals("", actual);
            }
        };

        interactor = new SignUpInteractor(postRepository, presenter);
        controller = new SignUpController(interactor);

        SignUpUserInputData testModel = new SignUpUserInputData("email@mail.utoronto.ca", "pass",
                "");

        controller.signUp(testModel);
    }

    @Test
    public void testSignUpAdminSuccess() {
        presenter = new SignUpOutputBoundary() {
            @Override
            public void updateViewModel(SignUpResponseModel responseModel) {
                boolean creation = responseModel.isCreationSuccess();
                String actual = responseModel.getMessage();

                assertTrue(creation);
                assertEquals("", actual);
            }
        };

        interactor = new SignUpInteractor(postRepository, presenter);
        controller = new SignUpController(interactor);

        SignUpUserInputData testModel = new SignUpUserInputData("email@mail.utoronto.ca", "pass",
                "123");

        controller.signUp(testModel);
    }

    @Test
    public void testSignUpAdminFailure() {
        presenter = new SignUpOutputBoundary() {
            @Override
            public void updateViewModel(SignUpResponseModel responseModel) {
                boolean creation = responseModel.isCreationSuccess();
                String actual = responseModel.getMessage();

                assertFalse(creation);
                assertEquals("admin password", actual);
            }
        };

        interactor = new SignUpInteractor(postRepository, presenter);
        controller = new SignUpController(interactor);

        SignUpUserInputData testModel = new SignUpUserInputData("email@mail.utoronto.ca",
                "password", "122");

        controller.signUp(testModel);
    }

    @Test
    public void testSignUpEmailExistsError() {
        presenter = new SignUpOutputBoundary() {
            @Override
            public void updateViewModel(SignUpResponseModel responseModel) {
                boolean creation = responseModel.isCreationSuccess();
                String actual = responseModel.getMessage();

                assertFalse(creation);
                assertEquals("email exists", actual);
            }
        };

        interactor = new SignUpInteractor(postRepository, presenter);
        controller = new SignUpController(interactor);

        postRepository.saveUser(new User(false, 0, "email@mail.utoronto.ca", "pass"));
        SignUpUserInputData testModel = new SignUpUserInputData("email@mail.utoronto.ca", "pass",
                "");

        controller.signUp(testModel);
    }

    @Test
    public void testSignUpIncorrectEmailError() {
        presenter = new SignUpOutputBoundary() {
            @Override
            public void updateViewModel(SignUpResponseModel responseModel) {
                boolean creation = responseModel.isCreationSuccess();
                String actual = responseModel.getMessage();

                assertFalse(creation);
                assertEquals("incorrect email", actual);
            }
        };

        interactor = new SignUpInteractor(postRepository, presenter);
        controller = new SignUpController(interactor);

        SignUpUserInputData testModel = new SignUpUserInputData("email", "pass", "");

        controller.signUp(testModel);
    }

    @Test
    public void testSignUpEmptyFieldError() {
        presenter = new SignUpOutputBoundary() {
            @Override
            public void updateViewModel(SignUpResponseModel responseModel) {
                boolean creation = responseModel.isCreationSuccess();
                String actual = responseModel.getMessage();

                assertFalse(creation);
                assertEquals("empty field", actual);
            }
        };

        interactor = new SignUpInteractor(postRepository, presenter);
        controller = new SignUpController(interactor);

        SignUpUserInputData testModel = new SignUpUserInputData("", "pass", "");

        controller.signUp(testModel);
    }

    @Test
    public void testSignUpWithObserver() {
        SignUpViewModel viewModel = new SignUpViewModel();
        presenter = new SignUpPresenter(viewModel);
        PropertyChangeListener observer = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("creation success")) {
                    assertTrue((boolean) evt.getNewValue());
                }

            }
        };

         viewModel.addObserver(observer);
         interactor = new SignUpInteractor(postRepository, presenter);
         controller = new SignUpController(interactor);

        SignUpUserInputData testModel = new SignUpUserInputData("email@mail.utoronto.ca", "pass",
                "");

        controller.signUp(testModel);
    }

}
