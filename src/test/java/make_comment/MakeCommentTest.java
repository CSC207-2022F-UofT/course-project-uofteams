package make_comment;

import entities.User;
import make_comment.use_case.MakeCommentGateway;
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
import java.util.Map;

public class MakeCommentTest {
    public static void main(String[] args) {
        MakeCommentGateway dsGateway = new MakeCommentGateway() {

            @Override
            public int getNumComments() {
                return 0;
            }

            @Override
            public void setNumComments(int newNumCommentCreated) {

            }

            @Override
            public void saveComment(Map<String, String> commentAttributes) {

            }

            @Override
            public void updatePostDB() {

            }

            @Override
            public Map<String, String> getCurrentUser() {
                return null;
            }

            @Override
            public List<String[]> getCurrentPosts() {
                return null;
            }
        };
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        SignUpPresenter presenter = new SignUpPresenter(signUpViewModel);
        SignUpInteractor interactor = new SignUpInteractor(dsGateway, presenter);
        SignUpController controller = new SignUpController(interactor);

        SignUpView signUpView = new SignUpView(controller);
        dsGateway.saveUser(new User(false, 0, "regan@mail.utoronto.ca", "a"));

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
