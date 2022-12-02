package make_post;

import com.opencsv.CSVReader;
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Tests the make_post use case.
 * Test coverage by lines:
 * drivers: 100%
 * interface_adapters: 100%
 * use_case: 100%
 *
 * It should be noted that the UI is not being tested here.
 */

public class MakePostTest {
    private User user;
    private MakePostDsGateway postRepository;
    private MakePostController controller;
    private MakePostInputBoundary interactor;
    private MakePostOutputBoundary presenter;
    private MakePostViewModel viewModel;
    private Map<String, Object> postBody = new HashMap<>();
    private String generalPath = "src/test/java/make_post/";
    private String postsPath = "src/test/java/make_post/posts.csv";
    private String numPostsCreatedPath = "src/test/java/make_post/numPostsCreated.csv";
    private String[] postHeaders = new String[]{"postID", "posterID", "title", "mainDescription", "tags", "collaborators",
            "deadline", "creationDate", "favouritedUsersIDs", "repliesIDs"};
    private String[] numPostsCreatedHeader = new String[]{"numPostsCreated"};
    private String testCreationDate = LocalDate.now().toString();


    @Before
    public void setUp() {
        this.setUpDefaultTestFiles(postsPath, postHeaders);
        this.setUpDefaultTestFiles(numPostsCreatedPath, numPostsCreatedHeader);
        this.user = new User(false, 1, "test@mail.utoronto.ca", "test");
        CurrentUser.setCurrentUser(user);
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
        PropertyChangeListener observer = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = "creation success";

                assertEquals(propertyName, evt.getPropertyName());
            }
        };
        viewModel.addObserver(observer);
        controller.passToMakePostInteractor(this.postBody);
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
        } catch (IOException | CsvException e) {
            System.out.println("File was not found.");
        }
    }

    @Test
    public void testMakePostSuccessfullyEmptyTags(){
        PropertyChangeListener observer = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = "creation success";

                assertEquals(propertyName, evt.getPropertyName());
            }
        };
        viewModel.addObserver(observer);
        ArrayList<String> emptyTags = new ArrayList<>();
        emptyTags.add("");
        postBody.put("tags", emptyTags);
        controller.passToMakePostInteractor(this.postBody);
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
        PropertyChangeListener observer = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = "creation failure";

                assertEquals(propertyName, evt.getPropertyName());
            }
        };
        viewModel.addObserver(observer);
        interactor = new MakePostInteractor(postRepository, presenter);
        controller = new MakePostController(interactor);
        postBody.put("deadline", "WrongFormat");
        controller.passToMakePostInteractor(this.postBody);
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
        PropertyChangeListener observer = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = "creation failure";

                assertEquals(propertyName, evt.getPropertyName());
            }
        };
        viewModel.addObserver(observer);
        postBody.put("deadline", "2200-11-29");
        controller.passToMakePostInteractor(this.postBody);
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
        controller.passToMakePostInteractor(this.postBody);
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
