package delete_post;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import delete_post.drivers.DeletePostDataAccess;
import entities.CurrentUser;
import entities.User;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import delete_post.interface_adapters.*;
import delete_post.use_case.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeletePostTest {
    DeletePostViewModel deletePostViewModel;
    DeletePostController controller;
    DeletePostInputBoundary interactor;
    DeletePostOutputBoundary presenter;
    DeletePostReaderInterface postReaderInterface;
    DeletePostDsGateway dataAccess;
    User user;

    final String postPath = "src/test/java/delete_post/posts.csv";
    final String userPath = "src/test/java/delete_post/users.csv";
    final String commentPath = "src/test/java/delete_post/comments.csv";
    final String generalPath = "src/test/java/delete_post/";
    List<String[]> postBody;
    List<String[]> userBody;

    @Before
    public void setup(){
        this.user = new User(false, 1, "test@mail.utoronto.ca", "test");
        CurrentUser.setCurrentUser(user);
        String[] postHeader = new String[]{"postID", "userID", "title", "mainDescription", "tags", "collaborators",
                "deadline", "creationDate", "favouritedUsersIDs", "repliesIDs"};
        String[] post1 = new String[]{"1", "1", "title0", "desc0", "tag0", "collab0", "2022-12-31",
                "2022-12-31", "1 2 3", "1"};
        String[] post2 = new String[]{"2", "1", "title1", "desc1", "tag0 tag1", "collab1", "2022-12-31",
                "2022-12-31", "1 2", "1"};
        String[] post3 = new String[]{"3", "2", "title2", "desc2", "tag1", "collab2", "2022-12-31",
                "2022-12-31", "", ""};
        String[] post4 = new String[]{"4", "3", "title2", "desc2", "tag1", "collab2", "2022-12-31",
                "2022-12-31", "", ""};
        postBody = new ArrayList<>();
        postBody.add(post1);
        postBody.add(post2);
        postBody.add(post3);
        postBody.add(post4);

        String[] userHeader = new String[]{"userID", "isAdmin", "email", "password", "listPosts","listFavourites"};
        String[] user1 = new String[]{"1", "true", "test@mail.utoronto.ca", "pass0", "1 2", "1 2"};
        String[] user2 = new String[]{"2", "false", "test2@mail.utoronto.ca", "pass0", "3", "1 2"};
        String[] user3 = new String[]{"3", "false", "test3@mail.utoronto.ca", "pass0", "4", "1"};
        userBody = new ArrayList<>();
        userBody.add(user1);
        userBody.add(user2);
        userBody.add(user3);

        String[] commentHeader = new String[]{"commentID","commenterID","body","creationDate"};
        String[] comment1 = new String[]{"1", "1", "cool post", "2022-12-31"};
        List<String[]> commentBody = new ArrayList<>();
        commentBody.add(comment1);

        createTestFile(postPath, postHeader, postBody);
        createTestFile(userPath, userHeader, userBody);
        createTestFile(commentPath, commentHeader, commentBody);

        deletePostViewModel = new DeletePostViewModel();
        presenter = new DeletePostPresenter(deletePostViewModel);
        postReaderInterface = new DeletePostFactory();
        dataAccess = new DeletePostDataAccess(generalPath, postReaderInterface);
        interactor = new DeletePostInteractor(presenter, dataAccess);
        controller = new DeletePostController(interactor);
    }
    @Test
    public void deletePostSuccessfully(){
        controller.delete(1, false);
        try{
            File posts = new File(postPath);
            FileReader postsReader = new FileReader(posts);
            CSVReader postsCsvReader = new CSVReader(postsReader);
            List<String[]> postsCsvBody = postsCsvReader.readAll();
            String[] postAttributes = postsCsvBody.get(1);
            String postID = postAttributes[0];
            String posterID = postAttributes[1];
            String title = postAttributes[2];
            String mainDesc = postAttributes[3];
            String tags = postAttributes[4];
            String collaborators = postAttributes[5];
            String deadline = postAttributes[6];
            String creationDate = postAttributes[7];
            String favouritedUsersIDs = postAttributes[8];
            String repliesIDs = postAttributes[9];

            assertEquals(postID, String.valueOf(2));
            assertEquals(posterID, String.valueOf(1));
            assertEquals(title, postBody.get(1)[2]);
            assertEquals(mainDesc, postBody.get(1)[3]);
            assertEquals(tags, postBody.get(1)[4]);
            assertEquals(collaborators, postBody.get(1)[5]);
            assertEquals(deadline, postBody.get(1)[6]);
            assertEquals(creationDate, postBody.get(1)[7]);
            assertEquals(favouritedUsersIDs, postBody.get(1)[8]);
            assertEquals(repliesIDs, postBody.get(1)[9]);
            postsCsvReader.close();

            File users = new File(userPath);
            FileReader usersReader = new FileReader(users);
            CSVReader usersCsvReader = new CSVReader(usersReader);
            List<String[]> usersCsvBody = usersCsvReader.readAll();
            assertEquals(usersCsvBody.get(1)[0], "1");
            assertEquals(usersCsvBody.get(1)[1], "true");
            assertEquals(usersCsvBody.get(1)[2], "test@mail.utoronto.ca");
            assertEquals(usersCsvBody.get(1)[3], "pass0");
            assertEquals(usersCsvBody.get(1)[4], "2");
            assertEquals(usersCsvBody.get(1)[5], "2");
            usersCsvReader.close();

            File comments = new File(commentPath);
            FileReader commentsReader = new FileReader(comments);
            CSVReader commentsCsvReader = new CSVReader(commentsReader);
            List<String[]> commentsCsvBody = commentsCsvReader.readAll();
            assertEquals(commentsCsvBody.get(0)[0], "commentID");
            assertEquals(commentsCsvBody.size(), 1);
            commentsCsvReader.close();
        } catch (IOException | CsvException e) {
            System.out.println("File could not be found.");
        }
    }
    @Test
    public void deletePostUserFailure(){
        User user = new User(false, 500, "test@mail.utoronto.ca", "test");
        CurrentUser.setCurrentUser(user);
        controller.delete(1, false);
        try{
        File posts = new File(postPath);
        FileReader postsReader = new FileReader(posts);
        CSVReader postsCsvReader = new CSVReader(postsReader);
        List<String[]> postsCsvBody = postsCsvReader.readAll();
        String[] postAttributes = postsCsvBody.get(1);
        String postID = postAttributes[0];
        String posterID = postAttributes[1];
        String title = postAttributes[2];
        String mainDesc = postAttributes[3];
        String tags = postAttributes[4];
        String collaborators = postAttributes[5];
        String deadline = postAttributes[6];
        String creationDate = postAttributes[7];
        String favouritedUsersIDs = postAttributes[8];
        String repliesIDs = postAttributes[9];

        assertEquals(postID, String.valueOf(1));
        assertEquals(posterID, String.valueOf(1));
        assertEquals(title, postBody.get(0)[2]);
        assertEquals(mainDesc, postBody.get(0)[3]);
        assertEquals(tags, postBody.get(0)[4]);
        assertEquals(collaborators, postBody.get(0)[5]);
        assertEquals(deadline, postBody.get(0)[6]);
        assertEquals(creationDate, postBody.get(0)[7]);
        assertEquals(favouritedUsersIDs, postBody.get(0)[8]);
        assertEquals(repliesIDs, postBody.get(0)[9]);
        postsCsvReader.close();

        File users = new File(userPath);
        FileReader usersReader = new FileReader(users);
        CSVReader usersCsvReader = new CSVReader(usersReader);
        List<String[]> usersCsvBody = usersCsvReader.readAll();
        assertEquals(usersCsvBody.get(1)[0], "1");
        assertEquals(usersCsvBody.get(1)[1], "true");
        assertEquals(usersCsvBody.get(1)[2], "test@mail.utoronto.ca");
        assertEquals(usersCsvBody.get(1)[3], "pass0");
        assertEquals(usersCsvBody.get(1)[4], "1 2");
        assertEquals(usersCsvBody.get(1)[5], "1 2");
        usersCsvReader.close();
        File comments = new File(commentPath);
        FileReader commentsReader = new FileReader(comments);
        CSVReader commentsCsvReader = new CSVReader(commentsReader);
        List<String[]> commentsCsvBody = commentsCsvReader.readAll();
        assertEquals(commentsCsvBody.size(), 2);
        commentsCsvReader.close();
        }
        catch (CsvException | IOException e) {
            System.out.println("File could not be found.");
        }
    }

    @After
    public void teardown(){
        File postFile = new File(postPath);
        File userFile = new File(userPath);
        File commentFile = new File(commentPath);
        postFile.delete();
        userFile.delete();
        commentFile.delete();
    }

    private void createTestFile(String path, String[] header, List<String[]> body){
        File file = new File(path);
        try {
            LinkedList<String[]> content = new LinkedList<>();
            content.add(header);
            content.addAll(body);
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
