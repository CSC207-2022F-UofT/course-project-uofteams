package drivers;

import delete_post.use_case.DeletePostDsGateway;
import entities.User;
import filter_post.use_case.FilterPostDsGateway;
import log_in.use_case.LogInDsGateway;
import make_post.use_case.MakePostDsGateway;
import make_post.use_case.make_post_exceptions.MakePostException;
import sign_up.use_case.SignUpDsGateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataAccess implements DeletePostDsGateway, FilterPostDsGateway, LogInDsGateway, MakePostDsGateway, SignUpDsGateway{
    private String generalPath;

    public DataAccess(String generalPath){
        this.generalPath = generalPath;
    }

    // Methods for delete post use case
    @Override
    public void removeFavourite(int postId, int userId) {

    }

    @Override
    public void removeTag(int postId, String tag) {

    }

    @Override
    public void deletePost(int postId) {

    }

    // methods for filter use case
    @Override
    public List<String[]> getPosts() {
        return null;
    }

    // methods for log in use case
    @Override
    public boolean checkUserEmailExists(String email) {
        return false;
    }

    @Override
    public boolean checkPasswordMatches(String email, String pass) {
        return false;
    }

    @Override
    public ArrayList<String> getUser(boolean success, String email, String pass) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    // methods for make post use case
    @Override
    public int getNumberOfPosts() {
        return 0;
    }

    @Override
    public void setNumberOfPosts(int newNumPostsCreated) {

    }

    @Override
    public void savePost(Map<String, String> postAttributes) {

    }

    @Override
    public int getCurrentUser() throws MakePostException {
        return 0;
    }

    // methods for sign up use case
    @Override
    public int getNumberUsers() {
        return 0;
    }

    @Override
    public void setNumberUsers(int numberUsers) {

    }

    @Override
    public List<String> getEmails() {
        return null;
    }

    @Override
    public void saveUser(User toSave) {

    }

    @Override
    public String getAdminPassword() {
        return null;
    }
}
