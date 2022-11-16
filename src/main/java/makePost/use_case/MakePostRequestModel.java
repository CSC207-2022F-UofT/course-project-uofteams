package makePost.use_case;

import java.time.LocalDate;
import java.util.List;
import entities.User;

public class MakePostRequestModel {
    private String title;
    private String mainDesc;
    private List<String> tags;
    private String collaborators;
    private LocalDate deadline;
    private LocalDate creationDate;
    private User poster;
    private int numPostsCreated;

    public MakePostRequestModel(String title, String mainDesc, List<String> tags, String collaborators,
                                LocalDate deadline, LocalDate creationDate, User poster, int numPostsCreated){
        this.title = title;
        this.mainDesc = mainDesc;
        this.tags = tags;
        this.collaborators = collaborators;
        this.deadline = deadline;
        this.creationDate = creationDate;
        this.poster = poster;
        this.numPostsCreated = numPostsCreated;
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

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getNumPostsCreated() {
        return numPostsCreated;
    }

    public void setNumPostsCreated(int numPostsCreated) {
        this.numPostsCreated = numPostsCreated;
    }
}
