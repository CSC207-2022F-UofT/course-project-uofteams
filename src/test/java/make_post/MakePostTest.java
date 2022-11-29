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

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Tests the Make Post use case.
 * Test coverage by lines:
 * drivers: 88%
 * interface_adapters: 93%
 * use_case: 98%
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
    public void testMakePostWrongDateFormat(){
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

    @Test
    public void testMakePostDeadlineTooFarInFuture(){
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
