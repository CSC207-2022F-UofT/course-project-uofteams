package delete_post;

import com.opencsv.CSVWriter;
import entities.Post;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import delete_post.interface_adapters.*;
import delete_post.use_case.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeletePostTest {
    DeletePostController controller;
    DeletePostInputBoundary interactor;
    DeletePostOutputBoundary presenter;
    DeletePostDsGateway dsgateway;

    String postPath = "src/test/java/delete_post/posts.csv";
    String userPath = "src/test/java/delete_post/users.csv";

    @Before
    public void setup(){
        String[] postHeader = new String[]{"postID", "userID", "title", "mainDescription", "tags", "collaborators",
                "deadline", "creationDate", "favouritedUsersIDs", "replyIDs"};
        String[] post0 = new String[]{"0", "0", "title0", "desc0", "tag0", "collab0", "deadline0",
                "creation0", "1 2", ""};
        String[] post1 = new String[]{"1", "0", "title1", "desc1", "tag0 tag1", "collab1", "deadline1",
                "creation1", "", "0"};
        String[] post2 = new String[]{"2", "1", "title2", "desc2", "tag1", "collab2", "deadline2",
                "creation2", "1", ""};
        List<String[]> postBody = new ArrayList<String[]>();
        postBody.add(post0);
        postBody.add(post1);
        postBody.add(post2);

        String[] userHeader = new String[]{"userID", "favourites", "posts", "password", "email"};
        String[] user0 = new String[]{"0", "", "0 1", "pass0", "email0"};
        String[] user1 = new String[]{"1", "0 2", "2", "pass1", "email1"};
        String[] user2 = new String[]{"2", "0", "", "pass2", "email2"};
        List<String[]> userBody = new ArrayList<String[]>();
        userBody.add(user0);
        userBody.add(user1);
        userBody.add(user2);

        createTestFile(postPath, postHeader, postBody);
        createTestFile(userPath, userHeader, userBody);
    }

    @Test
    public void deletePostAdminSuccess(){
        presenter
    }
    @Test
    public void deletePostUserSuccess(){}
    @Test
    public void deletePostUserFailure(){}
    @Test
    public void deletePostTimerSuccess(){}

    @Test
    public void deletePostCheckPostDeleted(){}
    @Test
    public void deletePostCheckFavListDeleted(){}
    @Test
    public void deletePostCheckUserListDeleted(){}

    @After
    public void teardown(){
        File postFile = new File(postPath);
        File userFile = new File(userPath);
        postFile.delete();
        userFile.delete();
    }

    private void createTestFile(String path, String[] header, List<String[]> body){
        File file = new File(path);
        try {
            LinkedList<String[]> content = new LinkedList<String[]>();
            content.add(header);
            for (String[] row : body){
                content.add(row);
            }
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error creating test file");
        }
    }
}
