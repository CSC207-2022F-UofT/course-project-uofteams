package make_post.use_case;

import entities.CurrentUser;
import entities.Post;
import make_post.use_case.make_post_exceptions.MakePostException;
import use_case_general.PostFactory;


import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class MakePostInteractor implements MakePostInputBoundary {
    private final MakePostDsGateway dataAccess;
    private final MakePostOutputBoundary presenter;

    private final PostFactory postFactory;

    public MakePostInteractor(MakePostDsGateway dataAccess, MakePostOutputBoundary presenter, PostFactory postFactory){
        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.postFactory = postFactory;
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

    @Override
    public void makePost(MakePostRequestModel requestModel) {
        Post newPost = postFactory.create(requestModel.getPoster(), requestModel.getTitle(), requestModel.getMainDesc(), requestModel.getTags(), requestModel.getCollaborators(),
                requestModel.getDeadline(), requestModel.getNumPostsCreated());
        Map<String, String> postAttributes = constructPostAttributes(newPost);

        try{
            this.dataAccess.savePost(postAttributes);
            //increase number of posts by 1
            this.dataAccess.setNumberOfPosts(this.dataAccess.getNumberOfPosts() + 1);
            MakePostResponseModel responseModel = this.makePostHelper(requestModel);
            this.presenter.updateViewModel(responseModel);
            //go back to the main view
            //close the MakePostView!
        }
        catch (MakePostException e) {
            MakePostResponseModel responseModel = new MakePostResponseModel(false, e.getMessage());
            this.presenter.updateViewModel(responseModel);
        }
    }

    public int getCurrentUser(){
        return CurrentUser.getCurrentUser().getId();
    }

    public int getNumPostsCreated(){
        return this.dataAccess.getNumberOfPosts();
    }


    private MakePostResponseModel makePostHelper(MakePostRequestModel requestModel) throws MakePostException {
        if (!checkDeadline(requestModel)) {
            throw new MakePostException("Deadline more than 6 months away or in the past");
        }
        else {
        return new MakePostResponseModel(true, "");}
    }
   @Override
    public MakePostOutputBoundary getPresenter() {
        return presenter;
    }

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
        if(!(tagsString.length() == 0)){
            tagsString.deleteCharAt(tagsString.length()-1);
        }
        else {
            tagsString = new StringBuilder("");
        }
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
