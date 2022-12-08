package favourite.use_case;

import entities.CurrentUser;
import entities.Post;
import entities.User;

import java.util.List;

/**
 * The interactor for the Favourite use case
 */
public class FavouriteInteractor implements FavouriteInputBoundary {
    private final FavouriteDsGateway dataAccess;
    private final FavouriteOutputBoundary presenter;

    /**
     * Initializes FavouriteInteractor
     *
     * @param dataAccess a FavouriteDatabaseAccess object passed as a FavouriteDsGateway object
     * @param presenter a FavouritePresenter object passed as a FavouriteOutputBoundary object
     */
    public FavouriteInteractor(FavouriteDsGateway dataAccess, FavouriteOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    /**
     * Favourites a post if the user has not already, unfavourites a post if the user has favourited it already.
     * @param requestModel a FavouriteRequestModel object that carries information about the post being
     *                     favourited/unfavourited
     */
    @Override
    public void favouritePost(FavouriteRequestModel requestModel) {
        int userID = CurrentUser.getCurrentUser().getId();
        User user = dataAccess.getUser(userID);
        Post post = dataAccess.getPost(requestModel.getPostId());

        // Checking if the user has already favourited this post
        boolean favourited = this.checkIfFavourited(post, user);

        // Unfavouriting post
        if (favourited) {
            this.unfavourite(post, user);
            updatePostDB(post);
            updateUserDB(user);
            FavouriteResponseModel responseModel = new FavouriteResponseModel(false, true);
            presenter.present(responseModel);
        }
        // Favouriting post
        else {
            favourite(post, user);
            updatePostDB(post);
            updateUserDB(user);
            FavouriteResponseModel responseModel = new FavouriteResponseModel(true, false);
            presenter.present(responseModel);
        }
    }

    /**
     * Helper that updates the User database
     * @param user a User object that is being saved in the database
     */
    private void updateUserDB(User user) {
        String[] userData = convertUserToString(user);
        dataAccess.saveUserInfo(userData, user.getId());
    }

    /**
     * Helper that updates the Post database
     *
     * @param post a Post object that is being saved in the database
     */
    private void updatePostDB(Post post) {
        String[] postData = convertPostToString(post);
        this.dataAccess.savePostInfo(postData, post.getID());
    }

    /**
     * Checks whether this user has already added to this user's favourites.
     *
     * @param post a Post object (the post being favourited/unfavourited)
     * @param user a User object (the user engaging with the post)
     * @return true if this user has already favourited this post, false otherwise
     */
    public boolean checkIfFavourited(Post post, User user) {
        List<Integer> favourites = user.getFavourites();
        return (favourites.contains(post.getID()));
    }

    /**
     * Adds user to the post's list of favourited users, and adds post to the user's list of favourited posts
     *
     * @param post a Post object (the post being favourited)
     * @param user a User object (the user that is trying to favourite this post)
     */
    private void favourite(Post post, User user) {
        post.addFavouritedUser(user.getId());
        user.addFavourite(post.getID());
    }

    /**
     * Removes user from the post's list of favourited users, and removes post from the user's list of favourited posts
     *
     * @param post a Post object (the post being unfavourited)
     * @param user a User object (the user that is trying to unfavourite this post)
     */
    private void unfavourite(Post post, User user) {
        post.removeFavouritedUser(user.getId());
        user.removeUserFavourite(post.getID());
    }

    /**
     * Converts a User object into a String array so that its data can be saved in the database
     *
     * @param user a User object that needs to be converted into a String array
     * @return a String array of the User's data to be saved in the database
     */
    private String[] convertUserToString(User user) {
        String[] userData = new String[6];
        String userID = Integer.toString(user.getId());
        String isAdmin = Boolean.toString(user.isAdmin());
        String email = user.getEmail();
        String password = user.getPassword();

        StringBuilder listPosts = new StringBuilder();
        List<Integer> posts = user.getPosts();
        for (int id : posts) {
            if (posts.indexOf(id) == 0) {
                listPosts = new StringBuilder(Integer.toString(id));
            } else {
                String idString = Integer.toString(id);
                listPosts.append(" ").append(idString);
            }
        }

        StringBuilder listFavourites = new StringBuilder();
        List<Integer> favourites = user.getFavourites();
        for (int id : favourites) {
            if (favourites.indexOf(id) == 0) {
                listFavourites = new StringBuilder(Integer.toString(id));
            } else {
                String idString = Integer.toString(id);
                listFavourites.append(" ").append(idString);
            }
        }

        userData[0] = userID;
        userData[1] = isAdmin;
        userData[2] = email;
        userData[3] = password;
        userData[4] = listPosts.toString();
        userData[5] = listFavourites.toString();

        return userData;
    }

    /**
     * Converts a Post object into a String array so that its data can be saved in the database
     *
     * @param post a Post object that needs to be converted into a String array
     * @return a String array of the Post's data to be saved in the database
     */
    private String[] convertPostToString(Post post) {
        String[] postData = new String[10];
        String postID = Integer.toString(post.getID());
        String posterID = Integer.toString(post.getUser());
        String title = post.getTitle();
        String mainDescription = post.getBody();

        StringBuilder tags = new StringBuilder();
        List<String> tagList = post.getTags();
        for (String tag : tagList) {
            if (tagList.indexOf(tag) == 0) {
                tags = new StringBuilder(tag);
            } else {
                tags.append(" ").append(tag);
            }
        }

        String collaborators = post.getCollaborators();

        String deadline = post.getDeadline().toString();

        String creationDate = post.getCreationDate().toString();

        StringBuilder favouritedUsersIDs = new StringBuilder();
        List<Integer> favUsers = post.getFavouritedUsers();
        for (int id : favUsers) {
            if (favUsers.indexOf(id) == 0) { // this is to make sure the string doesn't start with a space
                favouritedUsersIDs = new StringBuilder(Integer.toString(id));
            } else {
                String idString = Integer.toString(id);
                favouritedUsersIDs.append(" ").append(idString);
            }
        }

        StringBuilder repliesIDs = new StringBuilder();
        List<Integer> replies = post.getReplies();
        for (int id : replies) {
            if (replies.indexOf(id) == 0) {
                repliesIDs = new StringBuilder(Integer.toString(id));
            } else {
                String idString = Integer.toString(id);
                repliesIDs.append(" ").append(idString);
            }
        }

        postData[0] = postID;
        postData[1] = posterID;
        postData[2] = title;
        postData[3] = mainDescription;
        postData[4] = tags.toString();
        postData[5] = collaborators;
        postData[6] = deadline;
        postData[7] = creationDate;
        postData[8] = favouritedUsersIDs.toString();
        postData[9] = repliesIDs.toString();

        return postData;
    }
}