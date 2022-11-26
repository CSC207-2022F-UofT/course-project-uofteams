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
        int daysBetween = (int)DAYS.between(deadline, creationDate);
        if((deadline != null) && (((int)DAYS.between(creationDate, deadline) <= 182.5) && ((int)DAYS.between(creationDate, deadline) >= 0))){
            return true;
        }
        return false;
    }

    @Override
    public void makePost(MakePostRequestModel requestModel) {

        Post newPost = new Post(requestModel.getPoster(), requestModel.getTitle(), requestModel.getMainDesc(), requestModel.getTags(), requestModel.getCollaborators(),
                requestModel.getDeadline(), requestModel.getNumPostsCreated());
        //now we have got to save this new post to the DB.
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
        Map<String, String> postAttributes = new HashMap<>();
        postAttributes.put("postID", String.valueOf(postID));
        postAttributes.put("posterID", String.valueOf(posterID));
        postAttributes.put("title", title);
        postAttributes.put("mainDescription", mainDesc);
        StringBuilder tagsString = new StringBuilder();
        for(int i = 0; i < tags.size(); i++){
            tagsString.append(tags.get(i) + " ");
        }
        if(!(tagsString.length() == 0)){
            tagsString.deleteCharAt(tagsString.length()-1);
        }
        else {
            tagsString = new StringBuilder("");
        }
        postAttributes.put("tags", String.valueOf(tagsString));
        postAttributes.put("collaborators", collaborators);
        postAttributes.put("deadline", deadlineString);
        postAttributes.put("creationDate", creationDateString);
        postAttributes.put("favouritedUsersIDs", "");
        postAttributes.put("repliesIDs", "");

        try{
            MakePostResponseModel responseModel = this.makePostHelper(requestModel);
            this.presenter.updateViewModel(responseModel);
            this.dataAccess.savePost(postAttributes);
            //increase number of posts by 1
            this.dataAccess.setNumberOfPosts(this.dataAccess.getNumberOfPosts() + 1);
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
}