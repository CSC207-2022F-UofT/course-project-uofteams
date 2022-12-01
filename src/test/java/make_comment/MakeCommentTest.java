package make_comment;

import entities.User;
import make_comment.interface_adapter.makeCommentController;
import make_comment.interface_adapter.makeCommentPresenter;
import make_comment.interface_adapter.makeCommentViewModel;
import make_comment.ui.MakeCommentViewButton;
import make_comment.use_case.MakeCommentGateway;
import make_comment.use_case.MakeCommentInteractor;
import sign_up.interface_adapters.SignUpController;
import sign_up.interface_adapters.SignUpPresenter;
import sign_up.interface_adapters.SignUpViewModel;
import sign_up.ui.MasterLandingView;
import sign_up.ui.SignUpView;
import sign_up.use_case.SignUpDsGateway;
import sign_up.use_case.SignUpInteractor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
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
            public void updatePostDB(List<String[]> updatedPosts) {

            }

            @Override
            public List<String[]> getCurrentPosts() {
                return null;
            }
        };

        makeCommentViewModel mcvm = new makeCommentViewModel();
        makeCommentPresenter mcPresenter = new makeCommentPresenter(mcvm);
        MakeCommentInteractor MCI = new MakeCommentInteractor(dsGateway, mcPresenter);
        makeCommentController MCC = new makeCommentController(MCI);
        MakeCommentViewButton viewButton = new MakeCommentViewButton(0,MCC);
        Map<String, String> commentAttributes = new HashMap<>();
        commentAttributes.put("CommentID", "0");
        commentAttributes.put("commenterID", "0");
        commentAttributes.put("body", "test body 0");
        commentAttributes.put("creationDate", "2022-12-01");
        dsGateway.saveComment(commentAttributes);

        JFrame jFrame = new JFrame("Test");
        mcvm.addObserver(viewButton);
        jFrame.getContentPane().add(viewButton);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }
}
