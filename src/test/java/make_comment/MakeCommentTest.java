package make_comment;

import com.opencsv.CSVWriter;
import entities.CurrentUser;
import entities.User;
import make_comment.driver.MakeCommentDatabaseAccess;
import make_comment.interface_adapter.makeCommentController;
import make_comment.interface_adapter.makeCommentPresenter;
import make_comment.interface_adapter.makeCommentViewModel;
import make_comment.use_case.MakeCommentGateway;
import make_comment.use_case.MakeCommentInteractor;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MakeCommentTest {
    //set up test classes
    MakeCommentGateway gateway;
    makeCommentViewModel MCVM;
    makeCommentController MCC;
    makeCommentPresenter mcPresenter;
    MakeCommentInteractor MCI;

    // sets up tests.
    @Before
    public void setup(){
        String[] ncpHeader = new String[]{"numCommentsCreated"};
        String[] ppHeader = new String[]{"postID", "posterID", "title", "mainDescription", "tags", "collaborators",
                "deadline", "creationDate", "favouritedUsersIDs", "repliesIDs"};
        String[] cpHeader = new String[]{"commentID","commenterID","body","creationDate"};
        String numCommentPath = "src/test/java/make_comment/numCommentsCreated.csv";
        setupTestFiles(numCommentPath, ncpHeader);
        String postsPath = "src/test/java/make_comment/posts.csv";
        setupTestFiles(postsPath, ppHeader);
        String commentPath = "src/test/java/make_comment/comments.csv";
        setupTestFiles(commentPath, cpHeader);
        String filePath = "src/test/java/make_comment/";
        this.gateway = new MakeCommentDatabaseAccess(filePath);
        this.MCVM = new makeCommentViewModel();
        this.mcPresenter = new makeCommentPresenter(MCVM);
        this. MCI = new MakeCommentInteractor(gateway, mcPresenter);
        this.MCC = new makeCommentController(MCI);


    }


    /**
     * Test a post with appropriate input is created and updates on databases correctly.
     */
    @Test
    public void testMakeCommentSuccess(){
        PropertyChangeListener observer = evt -> {
            String propertyName = "creation success";

            assertEquals(propertyName, evt.getPropertyName());
        };
        User user = new User(false, 1, "regan@mail.utoronto.ca", "a");
        int postId1 = 1;
        String body = "Test Comment 0 by CurrentUser (1)";
        CurrentUser.setCurrentUser(user);

        this.MCVM.addObserver(observer);
        this.MCC.passToInteractor(body, postId1);
        assertEquals(1,gateway.getNumComments());
        String temp = gateway.getCurrentPosts().get(1)[9];
        assertEquals("0",temp);
        String body1 = "Test Comment 1 by CurrentUser (1)";
        this.MCC.passToInteractor(body1, postId1);
        assertEquals(2,gateway.getNumComments());
        String temp1 = gateway.getCurrentPosts().get(1)[9];
        assertEquals("0 1",temp1);

    }

    @Test
    public void testMakeCommentFailure(){
        PropertyChangeListener observer = evt -> {
            String propertyName = "creation failure";

            assertEquals(propertyName, evt.getPropertyName());
        };
        User user = new User(false, 1, "regan@mail.utoronto.ca", "a");
        int postId1 = 1;
        String body = "";
        CurrentUser.setCurrentUser(user);

        this.MCVM.addObserver(observer);
        this.MCC.passToInteractor(body, postId1);
        assertEquals(0,gateway.getNumComments());
        String temp = gateway.getCurrentPosts().get(1)[9];
        assertEquals("",temp);


    }

    private void setupTestFiles(String filepath, String[] headers){
        File file = new File(filepath);
        try {
            List<String[]> defaultCsvBody = new LinkedList<>();
            defaultCsvBody.add(headers);
            if(filepath.equals("src/test/java/make_comment/numCommentsCreated.csv")){
                String[] numCommentsCreated = new String[]{"0"};
                defaultCsvBody.add(numCommentsCreated);
            }
            if(filepath.equals("src/test/java/make_comment/posts.csv")){
                String[] samplePost = new String[]{"1", "1", "post1 title", "p1 main Description", "tag1,tag2","p1collaborators",
                "2022-12-15","2022-12-1","1",""};
                defaultCsvBody.add(samplePost);
            }
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(defaultCsvBody);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot find file or incorrect file format.");
        }

    }
    @After
    public void tearDown() {}


}
