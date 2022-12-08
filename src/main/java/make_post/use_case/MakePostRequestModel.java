package make_post.use_case;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
        if(tags.size() == 0){
            tags.add("");
        }
        this.collaborators = (String) postAttributes.get("collaborators");
        this.deadline = (LocalDate) postAttributes.get("deadline");

        this.poster = (Integer) postAttributes.get("poster");
        this.numPostsCreated = (int) postAttributes.get("numPostsCreated");
        this.postAttributes = postAttributes;
    }

    public String getTitle() {
        return title;
    }
    public String getMainDesc() {
        return mainDesc;
    }
    public List<String> getTags() {
        return tags;
    }
    public String getCollaborators() {
        return collaborators;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public int getPoster() {
        return poster;
    }
    public int getNumPostsCreated() {
        return numPostsCreated;
    }
}
