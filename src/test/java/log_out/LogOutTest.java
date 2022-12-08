package log_out;

import entities.CurrentUser;
import entities.User;
import log_out.interface_adapters.LogOutController;
import log_out.interface_adapters.LogOutControllerData;
import log_out.use_case.LogOutInputBoundary;
import log_out.use_case.LogOutInteractor;
import log_out.use_case.LogOutOutputBoundary;
import log_out.use_case.LogOutResponseModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LogOutTest {

    LogOutInputBoundary interactor;

    LogOutOutputBoundary presenter;

    LogOutController controller;


    public final List<User> users = new ArrayList<>();

    // method for testing purposes will update when csv is up
    public void addUser(User user){
        users.add(user);
    }

    //tests the logout use cases
    @Test
    public void logOutSuccess() {
        presenter = new LogOutOutputBoundary() {
            @Override
            public void present(LogOutResponseModel responseModel) {
                boolean creation = responseModel.isSuccess();

                assertTrue(creation);
                assertNull(CurrentUser.getCurrentUser());
            }
        };

        interactor = new LogOutInteractor(presenter);
        User user = new User(false, 0, "a", "b");
        CurrentUser.setCurrentUser(user);
        this.addUser(user);

        controller = new LogOutController(interactor);
        LogOutControllerData test = new LogOutControllerData("Log Out");

        controller.logOutInitializer(test);
    }
}
