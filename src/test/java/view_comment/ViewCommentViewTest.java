package view_comment;

import com.opencsv.CSVWriter;
import view_comment.drivers.ViewCommentDatabaseAccess;
import view_comment.interface_adapters.ViewCommentController;
import view_comment.interface_adapters.ViewCommentPresenter;
import view_comment.interface_adapters.ViewCommentViewModel;
import view_comment.ui.ViewCommentView;
import view_comment.use_case.ViewCommentInteractor;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.junit.After;
import org.junit.Before;

public class ViewCommentViewTest {
    ViewCommentInteractor viewCommentInteractor;
    ViewCommentDatabaseAccess viewCommentDatabaseAccess;
    ViewCommentController viewCommentController;
    ViewCommentPresenter viewCommentPresenter;
    ViewCommentViewModel viewCommentViewModel;



    @Before
    public void setup(){
        String[] ppHeader = new String[]{"postID", "posterID", "title", "mainDescription", "tags", "collaborators",
                "deadline", "creationDate", "favouritedUsersIDs", "repliesIDs"};
        String[] cpHeader = new String[]{"commentID","commenterID","body","creationDate"};
        String postsPath = "src/test/java/view_comment/posts.csv";
        setupTestFiles(postsPath, ppHeader);
        String commentPath = "src/test/java/view_comment/comments.csv";
        setupTestFiles(commentPath, cpHeader);
        String filePath = "src/test/java/view_comment/";
        this.viewCommentDatabaseAccess = new ViewCommentDatabaseAccess(filePath);
        this.viewCommentViewModel = new ViewCommentViewModel(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.viewCommentPresenter = new ViewCommentPresenter(viewCommentViewModel);
        this.viewCommentInteractor = new ViewCommentInteractor(viewCommentDatabaseAccess, viewCommentPresenter);
        this.viewCommentController = new ViewCommentController(viewCommentInteractor);
    }

    private void setupTestFiles(String filepath, String[] headers){
        File file = new File(filepath);
        try {
            List<String[]> defaultCsvBody = new LinkedList<>();
            defaultCsvBody.add(headers);
            if(filepath.equals("src/test/java/view_comment/comments.csv")){
                String[] sampleComment0 = new String[]{"0","1","sample Comment 0 long string for length long string for " +
                        "long string", "2022-12-05"};
                String[] sampleComment1 = new String[]{"1","0","sample Comment 1 long string for length long string for " +
                        "length long string for length long string for length", "2022-11-05"};
                String[] sampleComment2 = new String[]{"2","9","sample Comment 2 long string for length long string for " +
                        "length long string for length long string for length", "2022-10-05"};
                String[] sampleComment3 = new String[]{"3","8","sample Comment 3 long string for length long string for " +
                        "length long string for length long string for length", "2022-10-05"};
                String[] sampleComment4 = new String[]{"4","7","sample Comment 4 long string for length long string for " +
                        "length long string for length long string for length", "2022-10-05"};

                defaultCsvBody.add(sampleComment0);
                defaultCsvBody.add(sampleComment1);
                defaultCsvBody.add(sampleComment2);
                defaultCsvBody.add(sampleComment3);
                defaultCsvBody.add(sampleComment4);
            }
            if(filepath.equals("src/test/java/view_comment/posts.csv")){
                String[] samplePost1 = new String[]{"1", "1", "post1 title", "p1 main Description", "tag1,tag2","p1collaborators",
                        "2022-12-15","2022-12-1","1","0 1 2 3 4"};
                String[] samplePost2 = new String[]{"2", "1", "post1 title", "p1 main Description", "tag1,tag2","p1collaborators",
                        "2022-12-15","2022-12-1","1","1"};
                defaultCsvBody.add(samplePost1);
                defaultCsvBody.add(samplePost2);
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
    public static void main(String[] args) {
        String filePath = "src/test/java/view_comment/";
        ViewCommentDatabaseAccess viewCommentDatabaseAccess = new ViewCommentDatabaseAccess(filePath);
        ViewCommentViewModel viewCommentViewModel = new ViewCommentViewModel(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        ViewCommentPresenter viewCommentPresenter = new ViewCommentPresenter(viewCommentViewModel);
        ViewCommentInteractor viewCommentInteractor = new ViewCommentInteractor(viewCommentDatabaseAccess, viewCommentPresenter);
        ViewCommentController viewCommentController = new ViewCommentController(viewCommentInteractor);
        ViewCommentView viewCommentView = new ViewCommentView(10, viewCommentController);

        JFrame testFrame = new JFrame("Test");
        viewCommentViewModel.addObserver(viewCommentView);
        testFrame.getContentPane().add(viewCommentView);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        testFrame.pack();
        testFrame.setVisible(true);
    }
}

