package log_in;

import entities.User;
import log_in.interface_adapters.LogInController;
import log_in.interface_adapters.LogInPresenter;
import log_in.interface_adapters.LogInViewModel;
import log_in.ui.LogInView;
import log_in.use_case.LogInDsGateway;
import log_in.use_case.LogInInteractor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class logInViewTest {

    public static final List<User> users = new ArrayList<>();

    // method for testing purposes will update when csv is up
    public static void addUser(User user){
        users.add(user);
    }
    public static void main(String[] args) {
        LogInDsGateway gateway = new LogInDsGateway() {

            @Override
            public boolean checkUserEmailExists(String email) {
                return false;
            }

            @Override
            public boolean checkPasswordMatches(String email, String pass) {
                return false;
            }

            @Override
            public ArrayList<String> getUser(boolean success, String email, String pass) {
                return null;
            }

        };
        LogInViewModel logInViewModel = new LogInViewModel();
        LogInPresenter presenter = new LogInPresenter(new LogInViewModel());
        LogInInteractor interactor = new LogInInteractor(gateway, presenter);
        LogInController controller = new LogInController(interactor);

        LogInView logInView = new LogInView(controller);
        addUser(new User(false, 0, "a", "b"));

        logInViewModel.addObserver(logInView);

        JFrame jFrame = new JFrame("Test");
        jFrame.getContentPane().add(logInView);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
}
}






