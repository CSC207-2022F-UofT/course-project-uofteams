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






