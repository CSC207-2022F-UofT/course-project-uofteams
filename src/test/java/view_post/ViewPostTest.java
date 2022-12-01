package view_post;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view_post.drivers.ViewPostGateway;
import view_post.interface_adapters.ViewPostController;
import view_post.interface_adapters.ViewPostViewModel;
import view_post.ui.ViewPostView;
import view_post.use_case.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
public class ViewPostTest {

    private String partialPath = "src/test/java/view_post/";
    private ViewPostOutputBoundary presenter;
    private ViewPostDsGateway dataAccess;
    private ViewPostInputBoundary interactor;
    private ViewPostController controller;

    @Before
    public void setUp() {
        dataAccess = new ViewPostGateway(partialPath);
    }
    @After
    public void tearDown() {

    }
    @Test
    public void testRetrievePosts(){
        presenter = new ViewPostOutputBoundary() {
            @Override
            public void updateActivePost(ViewPostResponseModel responseModel) {
                String postEmail = responseModel.getPosterEmail();
                String postBody = responseModel.getPostBody();
                String postTags = responseModel.getPostTags();
                ArrayList<Integer> postReplies = responseModel.getPostReplies();
                String deadline = responseModel.getDeadline();
                String creationDate = responseModel.getCreationDate();
                String collaborators = responseModel.getCollaborators();
                int postID = responseModel.getPostID();
                String title = responseModel.getTitle();
                assertEquals("test@mail.utoronto.ca", postEmail);
                assertEquals("test", postBody);
                assertEquals("test1, test2", postTags);
                ArrayList<Integer> testPostReplies = new ArrayList<>(Arrays.asList(1, 2, 3));
                assertEquals(testPostReplies, postReplies);
                assertEquals("2023-01-31", deadline);
                assertEquals("2022-12-31", creationDate);
                assertEquals("test", collaborators);
                assertEquals(1, postID);
                assertEquals("test", title);
            }
        };
        interactor = new ViewPostInteractor(dataAccess, presenter);
        controller = new ViewPostController(interactor);
        controller.viewPost(1);
    }

}
