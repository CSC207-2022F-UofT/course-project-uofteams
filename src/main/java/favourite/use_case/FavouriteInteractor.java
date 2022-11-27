package favourite.use_case;

import entities.CurrentUser;
import entities.Post;
import entities.User;

import java.util.List;

/**
 * The FavouriteInteractor in the use case layer implements the FavouriteInputBoundary interface.
 * It takes in information from the outer layers of the architecture to run the use case by working directly
 * with the entities and ultimately send information back out into the UI.
 */
public class FavouriteInteractor implements FavouriteInputBoundary{
    private final FavouriteDSGateway dataAccess;
    private final FavouriteOutputBoundary presenter;

    /**
     * Initializes FavouriteInteractor
     *
     * @param dataAccess tells the FavouriteInteractor which post is being favourited/unfavourited
     */
    public FavouriteInteractor(FavouriteDSGateway dataAccess, FavouriteOutputBoundary presenter){
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    /**
     * Favourites a post if the user has not already, unfavourites a post if the user has favourited it already.
     *
     * @param requestModel tells the FavouriteInteractor which post is being favourited/unfavourited
     */
    @Override
    public void favouritepost(FavouriteRequestModel requestModel) {
        int userid = CurrentUser.getCurrentUser();
        User user = dataAccess.getUser(userid);
        Post post = dataAccess.getPost(requestModel.getPostId());

        // Checking if the user has already favourited this post
        boolean favourited = this.checkIfFavourited(post, user);

        // Unfavouriting post
        if (favourited){
            this.unfavourite(post, user);
            updatePostDB(post);
            updateUserDB(user);
            FavouriteResponseModel responseModel = new FavouriteResponseModel(false, true);
            presenter.present(responseModel);
        }
        // Favouriting post
        else{
            favourite(post, user);
            updatePostDB(post);
            updateUserDB(user);
            FavouriteResponseModel responseModel = new FavouriteResponseModel(true, false);
            presenter.present(responseModel);
        }
    }

    /**
     * Helper that updates the User database
     * @param user user being saved/updated in the database
     */
    private void updateUserDB(User user){
        String[] userdata = convertUserToString(user);
        dataAccess.saveUserInfo(userdata, user.getId());
    }

    /**
     * Helper that updates the Post database
     * @param post post being saved/updated in the database
     */
    private void updatePostDB(Post post){
        String[] postdata = convertPostToString(post);
        this.dataAccess.savePostInfo(postdata, post.getID());
    }


    /**
     * Checks whether this user has already added to this user's favourites.
     *
     * @param post post that this user is trying to favourite
     * @param user user that is trying to favourite this post
     * @return true if this user has already favourited this post, false otherwise
     */
    public boolean checkIfFavourited(Post post, User user){
        List<Integer> favourites = user.getFavourites();
        return (favourites.contains(post.getID()));
    }

    /**
     * Adds user to the post's list of favourited users, and adds post to the user's list of favourited posts
     *
     * @param post post that this user is trying to favourite
     * @param user user that is trying to favourite this post
     */
    private void favourite(Post post, User user){
        post.addFavouritedUser(user.getId()); // need to update entities to remove error line
        user.addFavourite(post.getID()); // need to update entities to remove error line
    }

    /**
     * Removes user from the post's list of favourited users, and removes post from the user's list of favourited posts
     *
     * @param post post that is being unfavourited
     * @param user user that is unfavouriting this post
     */
    private void unfavourite(Post post, User user){
        post.removeFavouritedUser(user.getId()); // need to update entities to remove error line
        user.removeUserFavourite(post.getID()); // need to update entities to remove error line
    }

    /**
     * Converts a User object into a String array so that its data can be saved in the database
     * @param user the User to be updated in the database
     * @return a String array of the User's data to be saved in the database
     */
    private String[] convertUserToString(User user){
        String[] userdata = new String[6];
        String userID = Integer.toString(user.getId());
        String isAdmin = Boolean.toString(user.isAdmin());
        String email = user.getEmail();
        String password = user.getPassword();

        String listPosts = "";
        List<Integer> posts = user.getPosts();
        for (int id: posts){
            String idString = Integer.toString(id);
            listPosts = listPosts + " " + idString;
        }
        if (listPosts.length() > 0){
            listPosts.replaceFirst(" ", "");
        }

        String listFavourites = "";
        List<Integer> favourites = user.getFavourites();
        for (int id: favourites){
            String idString = Integer.toString(id);
            listFavourites = listFavourites + " " + idString;
        }
        if (listFavourites.length() > 0){
            listPosts.replaceFirst(" ", "");
        }

        userdata[0] = userID;
        userdata[1] = isAdmin;
        userdata[2] = email;
        userdata[3] = password;
        userdata[4] = listPosts;
        userdata[5] = listFavourites;

        return userdata;
    }

    /**
     * Converts a Post object into a String array so that its data can be saved in the database
     * @param post the Post to be updated in the database
     * @return a String array of the Post's data to be saved in the database
     */
    private String[] convertPostToString(Post post){
        String[] postdata = new String[10];
        String postID = Integer.toString(post.getID());
        String posterID = Integer.toString(post.getUser());
        String title = post.getTitle();
        String mainDescription = post.getBody();

        String tags = "";
        List<String> taglist = post.getTags();
        for (String tag: taglist){
            tags = tags + " " + tag;
        }
        if (tags.length() > 0){
            tags.replaceFirst(" ", "");
        }

        String collaborators = post.getCollaborators();

        String deadline = post.getDeadline().toString();
        deadline.replace("-", " ");

        String creationDate = post.getCreationDate().toString();
        creationDate.replace("-", " ");

        String favouritedUsersIDs = "";
        List<Integer> favUsers = post.getFavouritedUsers();
        for (int id: favUsers){
            String idString = Integer.toString(id);
            favouritedUsersIDs = favouritedUsersIDs + " " + idString;
        }
        if (favouritedUsersIDs.length() > 0){
            favouritedUsersIDs.replaceFirst(" ", "");
        }

        String repliesIDs = "";
        List<Integer> replies = post.getReplies();
        for (int id: replies){
            String idString = Integer.toString(id);
            repliesIDs = repliesIDs + " " + idString;
        }
        if (repliesIDs.length() > 0){
            repliesIDs.replaceFirst(" ", "");
        }

        postdata[0] = postID;
        postdata[1] = posterID;
        postdata[3] = title;
        postdata[4] = mainDescription;
        postdata[5] = tags;
        postdata[6] = collaborators;
        postdata[7] = deadline;
        postdata[8] = creationDate;
        postdata[9] = favouritedUsersIDs;
        postdata[10] = repliesIDs;

        return postdata;
    }

}
