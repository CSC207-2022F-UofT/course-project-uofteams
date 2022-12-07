package make_post.use_case;

import entities.CurrentUser;
import entities.Post;
import make_post.use_case.make_post_exceptions.MakePostException;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class MakePostInteractor implements MakePostInputBoundary {
    private final MakePostDsGateway dataAccess;
    private final MakePostOutputBoundary presenter;
    private final PostFactory postFactory = new PostFactory();

    /**
     * Initialises the interactor for this use case.
     * @param dataAccess the database access class that saves the post to the db.
     * @param presenter the presenter that updates the view model about success/failure of post creation.
     */
    public MakePostInteractor(MakePostDsGateway dataAccess, MakePostOutputBoundary presenter){
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    /**
     * Checks whether the deadline is at most six months from the creationDate.
     * @return true if the above condition is met.
     */
    public boolean checkDeadline(MakePostRequestModel requestModel){
        LocalDate deadline = requestModel.getDeadline();
        LocalDate creationDate = LocalDate.now();
        int daysBetween = (int)DAYS.between(creationDate, deadline);
        if((deadline != null) && ((daysBetween <= 182.5) && (daysBetween >= 0))){
            return true;
        }
        return false;
    }

    /**
     * save the post to the db, throw errors if necessary, and switch back to the main view upon success.
     * @param requestModel a class containing the data that is used to create the post.
     */
    @Override
    public void makePost(MakePostRequestModel requestModel) {
        //make a Post entity.
        Post newPost = postFactory.create(requestModel.getPoster(), requestModel.getTitle(), requestModel.getMainDesc(), requestModel.getTags(), requestModel.getCollaborators(),
                requestModel.getDeadline(), requestModel.getNumPostsCreated());
        Map<String, String> postAttributes = constructPostAttributes(newPost);

        try{
            MakePostResponseModel responseModel = this.makePostHelper(requestModel);
            this.dataAccess.savePost(postAttributes);
            this.dataAccess.savePostToUser(newPost.getUser(), newPost.getID());
            //increase number of posts by 1
            this.dataAccess.setNumberOfPosts(this.dataAccess.getNumberOfPosts() + 1);
            this.presenter.updateViewModel(responseModel);
            //go back to the main view
            //close the MakePostView!
        }
        catch (MakePostException e) {
            MakePostResponseModel responseModel = new MakePostResponseModel(false, e.getMessage());
            this.presenter.updateViewModel(responseModel);
        }
    }

    /**
     * @return the current user's id.
     */
    public int getCurrentUser(){
        return CurrentUser.getCurrentUser().getId();
    }

    @Override
    public MakePostOutputBoundary getPresenter() {
        return this.presenter;
    }

    /**
     * @return the number of posts created so far.
     */
    public int getNumPostsCreated(){
        return this.dataAccess.getNumberOfPosts();
    }

    /**
     * this method returns the appropriate response model or throws an exception, which then tells the interactor whether
     * post creation is a success.
     * @param requestModel the post with its information.
     * @return a response model containing the output data.
     * @throws MakePostException
     */
    private MakePostResponseModel makePostHelper(MakePostRequestModel requestModel) throws MakePostException {
        if (!checkDeadline(requestModel)) {
            throw new MakePostException("Deadline more than 6 months away or in the past");
        }
        else {
        return new MakePostResponseModel(true, "");}
    }

    /**
     * converts the post's instance variables to the data types needed for saving the information to the database.
     * @param newPost the post that is to be saved to the db.
     * @return a map containing the information of the post that is to be saved to the db.
     */
    private Map<String, String> constructPostAttributes(Post newPost){
        Map<String, String> postAttributes = new HashMap<>();
        int posterID = newPost.getUser();
        String title = newPost.getTitle();
        String mainDesc = newPost.getBody();
        List<String> tags = newPost.getTags();
        String collaborators = newPost.getCollaborators();
        LocalDate deadline = newPost.getDeadline();
        String deadlineString = deadline.toString();
        int postID = newPost.getID();
        LocalDate creationDate = newPost.getCreationDate();
        String creationDateString = creationDate.toString();
        StringBuilder tagsString = new StringBuilder();
        for (String tag : tags) {
            tagsString.append(tag + " ");
        }
        tagsString.deleteCharAt(tagsString.length()-1);
        postAttributes.put("postID", String.valueOf(postID));
        postAttributes.put("posterID", String.valueOf(posterID));
        postAttributes.put("title", title);
        postAttributes.put("mainDescription", mainDesc);
        postAttributes.put("tags", String.valueOf(tagsString));
        postAttributes.put("collaborators", collaborators);
        postAttributes.put("deadline", deadlineString);
        postAttributes.put("creationDate", creationDateString);
        postAttributes.put("favouritedUsersIDs", "");
        postAttributes.put("repliesIDs", "");

        return postAttributes;
    }
}
