package sign_up;

import entities.User;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import sign_up.drivers.SignUpDatabaseAccess;
import sign_up.interface_adapters.SignUpController;
import sign_up.interface_adapters.SignUpPresenter;
import sign_up.interface_adapters.SignUpUserInputData;
import sign_up.interface_adapters.SignUpViewModel;
import sign_up.use_case.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Test the functionality of the sign up use case
 *
 * Included Test Coverage:
 *  - sign_up.use_case (92% line coverage)
 *  - sign_up.interface_adapters (93% line coverage)
 *  - sign_up.drivers.FilterPostDataAccess (37% coverage)
 *
 */
public class SignUpTest {
    SignUpDsGateway postRepository;
    SignUpOutputBoundary presenter;
    SignUpInputBoundary interactor;
    SignUpController controller;

    @Before
    public void setup() {
        // Create an anonymous class to mimi the data access

        postRepository = new SignUpDsGateway() {
            private int numPosts;
            public final List<String[]> users = new ArrayList<String[]>();


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
                for (String[] userInfo: users) {
                    emails.add(userInfo[2]);
                }
                return emails;
            }

            @Override
            public void saveUser(String[] toSave) {
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

    /**
     * Test that the SignUp use case correctly creates updates the output boundart when it
     * doesn't check the admin
     */
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

    /**
     * Test that the SignUp use case correctly updates the output boundary when it is given an
     * admin with a correct password
     */
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

    /**
     * Test the SignUp use case correctly updates the output boundary when it is given an admin with an
     * incorrect password
     */
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

    /**
     * Test that the SignUp use case correctly updates the output boundary when it is given an
     * email that already exists in the database
     */
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

        postRepository.saveUser(new String[]{"0", "false", "email@mail.utoronto.ca", "pass", "", ""});
        SignUpUserInputData testModel = new SignUpUserInputData("email@mail.utoronto.ca", "pass",
                "");

        controller.signUp(testModel);
    }

    /**
     * Test that the SignUp use case correctly updates the output boundary when it is given
     * an email in the improper format
     */
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

    /**
     * Test that the SignUp use case correctly updates the presenter when it is given an empty
     * email or password
     */
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

    /**
     * Test that the SignUp use case properly updates the output boundary and the output boundary
     * correctly updates the ViewModel, which correctly updates its observer
     */
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

    /**
     * Test that the database properly saves users
     */
    @Test
    public void testSaveUser() {
        SignUpDatabaseAccess databaseAccess = new SignUpDatabaseAccess("src/main/java/Database/");
        databaseAccess.saveUser(new String[]{"1", "true", "r@mail.utoronto.ca", "q", "", ""});
        List<String> emails = databaseAccess.getEmails();
        assertTrue(emails.contains("r@mail.utoronto.ca"));
    }
}
