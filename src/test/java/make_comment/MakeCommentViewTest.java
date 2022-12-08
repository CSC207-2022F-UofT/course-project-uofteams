package make_comment;

import entities.CurrentUser;
import entities.User;
import make_comment.interface_adapter.MakeCommentController;
import make_comment.interface_adapter.MakeCommentPresenter;
import make_comment.interface_adapter.MakeCommentViewModel;
import make_comment.ui.MakeCommentView;
import make_comment.use_case.CommentFactory;
import make_comment.use_case.MakeCommentDsGateway;
import make_comment.use_case.MakeCommentInteractor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeCommentViewTest {

    /**
     * Tests the make_comment use case.
     * Test coverage by lines:
     * ui: 100%
     * It should be noted that the drivers, interface_adapters and use_case is specifically tested here.
     */
    public static void main(String[] args) {
        MakeCommentDsGateway dsGateway = new MakeCommentDsGateway() {

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
            public void updatePostDatabase(List<String[]> updatedPosts) {

            }

            @Override
            public List<String[]> getCurrentPosts() {
                String[] p1 = new String[]{"1", "1", "post1", "descriptionp1", "cs", "collab", "2022-12-15", "2022-12-1", "1","2"};
                ArrayList<String[]> testList = new ArrayList<>();
                testList.add(p1);
                return testList;
            }
        };
        User user = new User(false, 1, "regan@mail.utoronto.ca", "a");
        CurrentUser.setCurrentUser(user);
        CommentFactory commentFactory = new CommentFactory();
        MakeCommentViewModel mcvm = new MakeCommentViewModel();
        MakeCommentPresenter mcPresenter = new MakeCommentPresenter(mcvm);
        MakeCommentInteractor MCI = new MakeCommentInteractor(dsGateway, mcPresenter, commentFactory);
        MakeCommentController MCC = new MakeCommentController(MCI);
        MakeCommentView viewButton = new MakeCommentView(MCC);
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
