package view_post.interface_adapters;

import java.util.ArrayList;

public class ViewPostViewModel {
    private final String posterEmail;
    private final String postBody;
    private final String postTags;
    private final ArrayList<Integer> postReplies;
    private final String deadline;
    private final String creationDate;
    private final String collaborators;
    private final int postID;

    public ViewPostViewModel(String posterEmail, String postBody, String postTags, ArrayList<Integer> postReplies,
                             String deadline, String creationDate, String collaborators, int postID){
        this.posterEmail = posterEmail;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postReplies = postReplies;
        this.deadline = deadline;
        this.creationDate = creationDate;
        this.collaborators = collaborators;
        this.postID = postID;
    }

    // getters
    public String getPosterEmail(){
        return this.posterEmail;
    }

    public String getPostBody() {
        return postBody;
    }

    public String getPostTags(){
        return this.postTags;
    }

    public ArrayList<Integer> getPostReplies() {
        return postReplies;
    }

    public String getDeadline(){
        return this.deadline;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCollaborators() {
        return collaborators;
    }

    public int getPostID() {
        return postID;
    }
}


