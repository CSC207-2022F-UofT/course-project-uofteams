package sign_up;

import entities.User;
import sign_up.drivers.SignUpDatabaseAccess;
import sign_up.interface_adapters.SignUpController;
import sign_up.interface_adapters.SignUpPresenter;
import sign_up.interface_adapters.SignUpViewModel;
import sign_up.ui.MasterLandingView;
import sign_up.ui.SignUpView;
import sign_up.use_case.SignUpDSGateway;
import sign_up.use_case.SignUpInteractor;

import javax.swing.*;
import java.util.List;

public class SignUpViewTest {
    public static void main(String[] args) {
        SignUpDSGateway dsGateway = new SignUpDSGateway() {
            @Override
            public int getNumberUsers() {
                return 0;
            }

            @Override
            public void setNumberUsers(int numberUsers) {

            }

            @Override
            public List<String> getEmails() {
                return null;
            }

            @Override
            public void saveUser(User toSave) {

            }

            @Override
            public String getAdminPassword() {
                return null;
            }
        };
        SignUpPresenter presenter = new SignUpPresenter(new SignUpViewModel());
        SignUpInteractor interactor = new SignUpInteractor(dsGateway, presenter);
        SignUpController controller = new SignUpController(interactor);

        SignUpView signUpView = new SignUpView(controller);

        JPanel logIn = new JPanel();
        MasterLandingView masterLandingView = new MasterLandingView(signUpView, logIn);

        JFrame jFrame = new JFrame("Test");
        jFrame.getContentPane().add(masterLandingView);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        signUpView.addObserver(masterLandingView.getButtonView());
    }
}
