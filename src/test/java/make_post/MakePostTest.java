package make_post;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import entities.CurrentUser;
import entities.User;
import make_post.drivers.MakePostDatabaseAccess;
import make_post.interface_adapters.MakePostController;
import make_post.interface_adapters.MakePostPresenter;
import make_post.interface_adapters.MakePostViewModel;
import make_post.use_case.MakePostDsGateway;
import make_post.use_case.MakePostInputBoundary;
import make_post.use_case.MakePostInteractor;
import make_post.use_case.MakePostOutputBoundary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.PropertyChangeListener;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Tests the make_post use case.
 * Test coverage by lines:
 * drivers: 98%
 * interface_adapters: 100%
 * use_case: 100%
 * The UI is not being tested here.
 */

public class MakePostTest {
    private MakePostDsGateway postRepository;
    private MakePostController controller;
    private MakePostInputBoundary interactor;
    private MakePostOutputBoundary presenter;
    private MakePostViewModel viewModel;
    private final Map<String, Object> postBody = new HashMap<>();
    private final String postsPath = "src/test/java/make_post/posts.csv";
    private final String usersPath = "src/test/java/make_post/users.csv";
    private final String[] postHeaders = new String[]{"postID", "posterID", "title", "mainDescription", "tags", "collaborators",
            "deadline", "creationDate", "favouritedUsersIDs", "repliesIDs"};
    private final String[] numPostsCreatedHeader = new String[]{"numPostsCreated"};
    private final String[] userHeaders = new String[]{"userID", "isAdmin", "email", "password", "listPosts", "listFavourites"};
    private final String testCreationDate = LocalDate.now().toString();


    @Before
    public void setUp() {
        this.setUpDefaultTestFiles(postsPath, postHeaders);
        String numPostsCreatedPath = "src/test/java/make_post/numPostsCreated.csv";
        this.setUpDefaultTestFiles(numPostsCreatedPath, numPostsCreatedHeader);
        this.setUpDefaultTestFiles(usersPath, userHeaders);
        User user = new User(false, 1, "test@mail.utoronto.ca", "test");
        CurrentUser.setCurrentUser(user);
        String generalPath = "src/test/java/make_post/";
        postRepository = new MakePostDatabaseAccess(generalPath);
        viewModel = new MakePostViewModel();
        presenter = new MakePostPresenter(viewModel);
        interactor = new MakePostInteractor(postRepository, presenter);
        controller = new MakePostController(interactor);
        postBody.put("title", "test");
        postBody.put("collaborators", "test");
        postBody.put("mainDescription", "test");
        postBody.put("deadline", "2022-12-31");
        List<String> tags = new ArrayList<>(Arrays.asList("tag1", "tag2", "tag3"));
        postBody.put("tags", tags);
    }

    @After
    public void tearDown() {}

    /**
     * test that a post is added to the database if all the inputs are correct.
     */
    @Test
    public void testMakePostSuccessfully(){
        PropertyChangeListener observer = evt -> {
            String propertyName = "creation success";

            assertEquals(propertyName, evt.getPropertyName());
        };
        viewModel.addObserver(observer);
        controller.executeMakePost(this.postBody);
        assertEquals(1, postRepository.getNumberOfPosts());
        try{
            File posts = new File(postsPath);
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
            assertEquals(title, postBody.get("title"));
            assertEquals(mainDesc, postBody.get("mainDescription"));
            assertEquals("tag1 tag2 tag3", tags);
            assertEquals(collaborators, postBody.get("collaborators"));
            assertEquals(deadline, postBody.get("deadline").toString());
            assertEquals(creationDate, testCreationDate);
            assertEquals("", favouritedUsersIDs);
            assertEquals("", repliesIDs);
            postsCsvReader.close();

            File users = new File(usersPath);
            FileReader usersReader = new FileReader(users);
            CSVReader usersCsvReader = new CSVReaderBuilder(usersReader).withSkipLines(1).build();
            List<String[]> usersCsvBody = usersCsvReader.readAll();
            assertEquals(1, usersCsvBody.size());
            assertEquals("1", usersCsvBody.get(0)[0]);
            assertEquals("false", usersCsvBody.get(0)[1]);
            assertEquals("test@mail.utoronto.ca", usersCsvBody.get(0)[2]);
            assertEquals("test", usersCsvBody.get(0)[3]);
            assertEquals("1", usersCsvBody.get(0)[4]);
            assertEquals("", usersCsvBody.get(0)[5]);
            usersReader.close();
        } catch (IOException | CsvException e) {
            System.out.println("File was not found.");
        }
    }

    @Test
    public void testMakePostSuccessfullyEmptyTags(){
        PropertyChangeListener observer = evt -> {
            String propertyName = "creation success";

            assertEquals(propertyName, evt.getPropertyName());
        };
        viewModel.addObserver(observer);
        ArrayList<String> emptyTags = new ArrayList<>();
        postBody.put("tags", emptyTags);
        controller.executeMakePost(this.postBody);
        assertEquals(1, postRepository.getNumberOfPosts());
        try{
            File posts = new File(postsPath);
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
            assertEquals(title, postBody.get("title"));
            assertEquals(mainDesc, postBody.get("mainDescription"));
            assertEquals("", tags);
            assertEquals(collaborators, postBody.get("collaborators"));
            assertEquals(deadline, postBody.get("deadline").toString());
            assertEquals(creationDate, testCreationDate);
            assertEquals("", favouritedUsersIDs);
            assertEquals("", repliesIDs);
        } catch (IOException | CsvException e) {
            System.out.println("File was not found.");
        }
    }

    /**
     * test if a post is added with a deadline in the wrong format.
     * success if the post is not added to the database.
     */
    @Test
    public void testMakePostWrongDateFormat(){
        PropertyChangeListener observer = evt -> {
            String propertyName = "creation failure";

            assertEquals(propertyName, evt.getPropertyName());
        };
        viewModel.addObserver(observer);
        interactor = new MakePostInteractor(postRepository, presenter);
        controller = new MakePostController(interactor);
        postBody.put("deadline", "WrongFormat");
        controller.executeMakePost(this.postBody);
        assertEquals(0, postRepository.getNumberOfPosts());
        try{
            File posts = new File(postsPath);
            FileReader postsReader = new FileReader(posts);
            CSVReader postsCsvReader = new CSVReader(postsReader);
            List<String[]> postsCsvBody = postsCsvReader.readAll();
            assertEquals(1, postsCsvBody.size());
        } catch (IOException | CsvException e) {
            System.out.println("File was not found.");
        }
    }
    /**
     * test if a post is added with a deadline more than 6 months in the future.
     * success if the post is not added to the database.
     */
    @Test
    public void testMakePostDeadlineTooFarInFuture(){
        PropertyChangeListener observer = evt -> {
            String propertyName = "creation failure";

            assertEquals(propertyName, evt.getPropertyName());
        };
        viewModel.addObserver(observer);
        postBody.put("deadline", "2200-11-29");
        controller.executeMakePost(this.postBody);
        assertEquals(0, postRepository.getNumberOfPosts());
        try{
            File posts = new File(postsPath);
            FileReader postsReader = new FileReader(posts);
            CSVReader postsCsvReader = new CSVReader(postsReader);
            List<String[]> postsCsvBody = postsCsvReader.readAll();
            assertEquals(1, postsCsvBody.size());
        } catch (IOException | CsvException e) {
            System.out.println("File was not found.");
        }
    }

    @Test
    public void testMakePostNoTitle(){
        PropertyChangeListener observer = evt -> {
            String propertyName = "creation failure";

            assertEquals(propertyName, evt.getPropertyName());
        };
        viewModel.addObserver(observer);
        interactor = new MakePostInteractor(postRepository, presenter);
        controller = new MakePostController(interactor);
        postBody.put("title", "");
        controller.executeMakePost(this.postBody);
        assertEquals(0, postRepository.getNumberOfPosts());
        try{
            File posts = new File(postsPath);
            FileReader postsReader = new FileReader(posts);
            CSVReader postsCsvReader = new CSVReader(postsReader);
            List<String[]> postsCsvBody = postsCsvReader.readAll();
            assertEquals(1, postsCsvBody.size());
        } catch (IOException | CsvException e) {
            System.out.println("File was not found.");
        }
    }

    /**
     * tests if the correct error messages are being thrown.
     */
    @Test
    public void testMakePostDsGatewayCatchIOException(){
        String badPath = "/bad/path.csv";
        postRepository = new MakePostDatabaseAccess(badPath);
        interactor = new MakePostInteractor(postRepository, presenter);
        controller = new MakePostController(interactor);
        controller.executeMakePost(this.postBody);
    }

    private void setUpDefaultTestFiles(String filepath, String[] headers){
        File file = new File(filepath);
        try {
            List<String[]> defaultCsvBody = new LinkedList<>();
            defaultCsvBody.add(headers);
            if(filepath.equals("src/test/java/make_post/numPostsCreated.csv")){
                String[] numPostsCreated = new String[]{"0"};
                defaultCsvBody.add(numPostsCreated);
            }
            if(filepath.equals("src/test/java/make_post/users.csv")){
                String[] user = new String[]{"1", "false", "test@mail.utoronto.ca", "test", "", ""};
                defaultCsvBody.add(user);
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

}
