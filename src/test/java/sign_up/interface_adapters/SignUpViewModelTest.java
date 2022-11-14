package sign_up.interface_adapters;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.After;
import sign_up.use_case.SignUpDataAccessInterface;
import sign_up.use_case.SignUpInputBoundary;
import sign_up.use_case.SignUpRequestModel;
import sign_up.use_case.SignUpResponseModel;

import java.util.ArrayList;

/*
* Define a test class for the Sign Up ViewModel
*
* Will test all public methods, with corner cases
* */
public class SignUpViewModelTest {
    private static SignUpViewModel testViewModel;
    private static DummyView dummyView;
    private static DummyInputBoundary dummyInputBoundary;
    private static DummyDataAccess dummyDataAccess;

    private static class DummyView implements View{
        private boolean result;
        private String message;

        @Override
        public void update(boolean result, String message) {
            this.message = message;
            this.result = result;
        }

        @Override
        public void view() {}
    }

    private static class DummyInputBoundary implements SignUpInputBoundary{
        private SignUpRequestModel givenData;
        @Override
        public void signUp(SignUpRequestModel requestModel) {
            this.givenData = requestModel;
        }
    }

    private static class DummyDataAccess implements SignUpDataAccessInterface{
        @Override
        public int getNumberUsers() {
            return 0;
        }
        @Override
        public void setNumberUsers(int numUsers) {

        }
        @Override
        public boolean checkUserEmailExists(String email) {
            return false;
        }
        @Override
        public void saveUser(Object toSave, String type) {

        }
        @Override
        public String getAdminPassword() {
            return "123";
        }
    }

    @Before
    public void init() {
        dummyView = new DummyView();
        dummyInputBoundary = new DummyInputBoundary();
        dummyDataAccess = new DummyDataAccess();

        testViewModel = new SignUpViewModel(dummyDataAccess, dummyView);
    }

    @After
    public void teardown() {}

    @Test
    public void testSignUpAdminInterpreterNoAdmin() {
        testViewModel.signUp("testEmail", "testPass", "");
        boolean actual = dummyInputBoundary.givenData.checkAdmin;

        Assertions.assertFalse(actual);
    }

    @Test
    public void testSignUpAdminInterpreterYesAdmin() {
        testViewModel.signUp("testEmail", "testPass", "123");
        boolean actual = dummyInputBoundary.givenData.checkAdmin;

        Assertions.assertTrue(actual);
    }

    @Test
    public void testSignUpEmailCorrect() {
        testViewModel.signUp("testEmail", "testPass", "");
        String actual = dummyInputBoundary.givenData.email;
        String expected = "testEmail";

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testSignUpPasswordCorrect() {
        testViewModel.signUp("testEmail", "testPass", "");
        String actual = dummyInputBoundary.givenData.password;
        String expected = "testPass";

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testPresentSuccessCase() {
        SignUpResponseModel testResponse = new SignUpResponseModel(true, "");
        testViewModel.present(testResponse);
        boolean actual = dummyView.result;

        Assertions.assertTrue(actual);
    }

    @Test
    public void testPresentFailureCase() {
        SignUpResponseModel testResponse = new SignUpResponseModel(false, "e");
        testViewModel.present(testResponse);
        boolean actual = dummyView.result;

        Assertions.assertFalse(actual);
    }

    @Test
    public void testPresentSuccessAndEmptyMessage() {
        SignUpResponseModel testResponse = new SignUpResponseModel(true, "");
        testViewModel.present(testResponse);

        Assertions.assertTrue(dummyView.result && dummyView.message.isEmpty());
    }

    @Test
    public void testPresentFailureAndNonemptyMessage() {
        SignUpResponseModel testResponse = new SignUpResponseModel(false, "e");
        testViewModel.present(testResponse);

        Assertions.assertTrue(!dummyView.result && !dummyView.message.isEmpty());
    }
}
