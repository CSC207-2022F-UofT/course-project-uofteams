package deadline_timer.use_case;

import entities.Post;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class PostFactory implements PostReader{
    public Post createPost(Integer posterId, String title, String mainDesc, List<String> tags, String collaborators,
                           LocalDate deadline, int id) {
        return new Post(posterId, title, mainDesc, tags, collaborators, deadline, id);
    }

    @Override
    public Object readPost(HashMap<String, Object> postInfo) {
        return new Post((Integer) postInfo.get("posterId"), (String) postInfo.get("title"),
                (String) postInfo.get("mainDescription"), (List<String>) postInfo.get("tags"),
                (String) postInfo.get("collaborators"), (LocalDate) postInfo.get("deadline"),
                (LocalDate) postInfo.get("creationDate"), (Integer) postInfo.get("postId"),
                (List<Integer>) postInfo.get("favourites"), (List<Integer>) postInfo.get("replies"));
    }
}
