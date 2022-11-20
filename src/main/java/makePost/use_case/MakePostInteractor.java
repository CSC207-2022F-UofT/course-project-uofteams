package makePost.use_case;

import entities.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

public class MakePostInteractor implements MakePostInputBoundary {
    private final MakePostDataAccessInterface dataAccess;
    private final MakePostOutputBoundary presenter;

    public MakePostInteractor(MakePostDataAccessInterface dataAccess, MakePostOutputBoundary presenter){
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    /**
     * Checks whether the deadline is at most six months from the creationDate.
     * @return true if the above condition is met.
     */
    public boolean checkDeadline(MakePostRequestModel mprm){
        LocalDate deadline = mprm.getDeadline();
        LocalDate creationDate = LocalDate.now();
        if((deadline != null) && (DAYS.between(deadline, creationDate) <= 182.5)){
            return true;
        }
        return false;
    }

    @Override
    public void makePost(MakePostRequestModel mprm) {
        if(checkDeadline(mprm) == false){
            //raise an error
        }
        Post newPost = new Post(mprm.getPoster().getId(), mprm.getTitle(), mprm.getMainDesc(), mprm.getTags(), mprm.getCollaborators(),
                mprm.getDeadline(), mprm.getNumPostsCreated());
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
        List<Integer> favouritedUsersIDs = new ArrayList<>();
        List<Integer> repliesIDs = new ArrayList<>();
        Map<String, String> postAttributes = new HashMap<>();
        postAttributes.put("postID", String.valueOf(postID));
        postAttributes.put("posterID", String.valueOf(posterID));
        postAttributes.put("title", title);
        postAttributes.put("mainDescription", mainDesc);
        StringBuilder tagsString = new StringBuilder();
        for(int i = 0; i < tags.size(); i++){
            tagsString.append(tags.get(i) + " ");
            tagsString.deleteCharAt(tags.size()-1);
        }
        postAttributes.put("tags", String.valueOf(tagsString));
        postAttributes.put("collaborators", collaborators);
        postAttributes.put("deadline", deadlineString);
        postAttributes.put("creationDate", creationDateString);
        postAttributes.put("favouritedUsersIDs", "");
        postAttributes.put("repliedIDs", "");

        this.dataAccess.savePost(postAttributes);
        //increase number of posts by 1
        this.dataAccess.setNumberOfPosts(this.dataAccess.getNumberOfPosts() + 1);
        //go back to the main view
        this.dataAccess.setTags(Integer.parseInt(postAttributes.get("postID")), postAttributes.get("tags"));
        //close the MakePostView!


    }

    public Map<String, String> getCurrentUser(){
        return this.dataAccess.getCurrentUser();
    }

    @Override
    public List<Map<String, Object>> getFavouritedUsers() {
        return this.dataAccess.getFavouritedUsers();
    }

    public int getNumPostsCreated(){
        return this.dataAccess.getNumberOfPosts();
    }

    public List<Map<String, Object>> getReplies(){
        return this.dataAccess.getReplies();
    }

    @Override
    public int getCurrentPostID() {
        return this.dataAccess.getCurrentPostID();
    }

    @Override
    public String getCreationDate() {
        return this.dataAccess.getCreationDate();
    }

}
