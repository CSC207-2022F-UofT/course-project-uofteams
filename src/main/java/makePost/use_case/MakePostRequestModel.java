package makePost.use_case;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import entities.User;

public class MakePostRequestModel {
    private String title;
    private String mainDesc;
    private List<String> tags;
    private String collaborators;
    private LocalDate deadline;
    private int poster;
    private int numPostsCreated;

    private Map<String, Object> postAttributes;

    public MakePostRequestModel(Map<String, Object> postAttributes){
        this.title = (String) postAttributes.get("title");
        this.mainDesc = (String) postAttributes.get("mainDescription");
        this.tags = (List<String>) postAttributes.get("tags");
        this.collaborators = (String) postAttributes.get("collaborators");
        this.deadline = (LocalDate) postAttributes.get("deadline");

        this.poster = (Integer) postAttributes.get("poster");
        this.numPostsCreated = (int) postAttributes.get("numPostsCreated");
        this.postAttributes = postAttributes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainDesc() {
        return mainDesc;
    }

    public void setMainDesc(String mainDesc) {
        this.mainDesc = mainDesc;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(String collaborators) {
        this.collaborators = collaborators;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public int getNumPostsCreated() {
        return numPostsCreated;
    }

    public void setNumPostsCreated(int numPostsCreated) {
        this.numPostsCreated = numPostsCreated;
    }
}
