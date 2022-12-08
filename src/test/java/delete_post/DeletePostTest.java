package delete_post;

import com.opencsv.CSVWriter;
import delete_post.drivers.DeletePostDataAccess;
import entities.CurrentUser;
import entities.Post;
import entities.User;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeletePostTest {
    DeletePostController controller;
    DeletePostInputBoundary interactor;
    DeletePostOutputBoundary presenter;
    DeletePostDsGateway dsgateway;
    DeletePostDataAccess dataAccess;
    CurrentUser currentUser;

    String postPath = "src/test/java/delete_post/posts.csv";
    String userPath = "src/test/java/delete_post/users.csv";
    String commentPath = "src/test/java/delete_post/comments.csv";

    @Before
    public void setup(){
        PostReaderInterface postFactory = new PostFactory();
        dataAccess = new DeletePostDataAccess(postPath, userPath, commentPath, postFactory);
        currentUser = new CurrentUser();
    }

    @Test
    public void deletePostAdminSuccess(){
        User user1 = new User(true, 1, "email1@mail.utoronto.ca", "pass1");
        currentUser.setCurrentUser(user1);
        presenter = new DeletePostOutputBoundary() {
            @Override
            public void updateViewModel(DeletePostResponseModel responseModel) {
                assertTrue(responseModel.deleteSuccess());
            }
        };
        dsgateway = new DeletePostDsGateway() {
            @Override
            public Post getPost(int postId) {
                ArrayList<String> tags = new ArrayList<>();
                LocalDate deadline = LocalDate.parse("2022-12-25");
                LocalDate creationDate = LocalDate.parse("2022-11-30");
                ArrayList<Integer> favourites = new ArrayList<>();
                favourites.add(1);
                favourites.add(2);
                ArrayList<Integer> replies = new ArrayList<>();
                Post post = new Post(0, "title0", "desc0", tags, "collab0",
                        deadline, creationDate, 0, favourites, replies);
                assertTrue(currentUser.getIsAdmin());
                return post;
            }
            @Override
            public void removeFavourite(int postId, int userId) {
                assertTrue(userId == 1 || userId == 2);
            }

            @Override
            public void removeUser(int postId, int userId) {
                assertEquals(0, userId);
            }

            @Override
            public void deletePost(int postId) {
                assertEquals(0, postId);
            }

            @Override
            public void deleteComment(int commentId) {
                assertNull(commentId);
            }
        };
        interactor = new DeletePostInteractor(presenter, dsgateway);
        controller = new DeletePostController(interactor);
        controller.delete(0, false);
    }
    @Test
    public void deletePostUserSuccess(){
        User user = new User(false, 1, "email@mail.utoronto.ca", "pass");
        currentUser.setCurrentUser(user);
        presenter = new DeletePostOutputBoundary() {
            @Override
            public void updateViewModel(DeletePostResponseModel responseModel) {
                assertTrue(responseModel.deleteSuccess());
            }
        };
        dsgateway = new DeletePostDsGateway() {
            @Override
            public Post getPost(int postId) {
                ArrayList<String> tags = new ArrayList<>();
                LocalDate deadline = LocalDate.parse("2022-12-25");
                LocalDate creationDate = LocalDate.parse("2022-11-30");
                ArrayList<Integer> favourites = new ArrayList<>();
                ArrayList<Integer> replies = new ArrayList<>();
                replies.add(0);
                Post post = new Post(1, "title1", "desc1", tags, "collab1",
                        deadline, creationDate, 1, favourites, replies);
                assertEquals(currentUser.getCurrentUser().getId(), post.getUser());
                assertFalse(currentUser.getIsAdmin());
                return post;
            }

            @Override
            public void removeFavourite(int postId, int userId) {
                assertNull(userId);
            }

            @Override
            public void removeUser(int postId, int userId) {
                assertEquals(1, userId);
            }

            @Override
            public void deletePost(int postId) {
                assertEquals(1, postId);
            }

            @Override
            public void deleteComment(int commentId) {
                assertEquals(0, commentId);
            }
        };
        interactor = new DeletePostInteractor(presenter, dsgateway);
        controller = new DeletePostController(interactor);
        controller.delete(1, false);
    }
    @Test
    public void deletePostFail(){
        User user0 = new User(false, 0, "email0@mail.utoronto.ca", "pass0");
        currentUser.setCurrentUser(user0);
        presenter = new DeletePostOutputBoundary() {
            @Override
            public void updateViewModel(DeletePostResponseModel responseModel) {
                assertFalse(responseModel.deleteSuccess());
            }
        };
        interactor = new DeletePostInteractor(presenter, dsgateway);
        controller = new DeletePostController(interactor);
        controller.delete(2, false);
    }

    @After
    public void teardown(){
    }
}
