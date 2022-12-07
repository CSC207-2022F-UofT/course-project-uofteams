package sign_up;

import entities.User;
import sign_up.interface_adapters.SignUpController;
import sign_up.interface_adapters.SignUpPresenter;
import sign_up.interface_adapters.SignUpViewModel;
import sign_up.ui.MasterLandingView;
import sign_up.ui.SignUpView;
import sign_up.use_case.SignUpDsGateway;
import sign_up.use_case.SignUpInteractor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Test the SignUp GUI by showing it, so it can be inspected
 */
public class SignUpViewTest {
    public static void main(String[] args) {
        SignUpDsGateway dsGateway = new SignUpDsGateway() {
            ArrayList<String[]> userArrayList = new ArrayList<String[]>();
            @Override
            public int getNumberUsers() {
                return 0;
            }

            @Override
            public void setNumberUsers(int numberUsers) {

            }

            @Override
            public List<String> getEmails() {
                ArrayList<String> toReturn = new ArrayList<String>();
                for (String[] user: userArrayList) {
                    toReturn.add(user[2]);
                }
                return toReturn;
            }

            @Override
            public void saveUser(String[] toSave) {
                userArrayList.add(toSave);
            }

            @Override
            public String getAdminPassword() {
                return "123";
            }
        };
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        SignUpPresenter presenter = new SignUpPresenter(signUpViewModel);
        SignUpInteractor interactor = new SignUpInteractor(dsGateway, presenter);
        SignUpController controller = new SignUpController(interactor);

        SignUpView signUpView = new SignUpView(controller);
        dsGateway.saveUser(new String[]{"0", "false", "regan@mail.utoronto.ca", "a", "", ""});

        JPanel logIn = new JPanel();
        MasterLandingView masterLandingView = new MasterLandingView(signUpView, logIn);

        signUpView.addObserver(masterLandingView);
        signUpViewModel.addObserver(signUpView);

        JFrame jFrame = new JFrame("Test");
        jFrame.getContentPane().add(masterLandingView);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }
}
