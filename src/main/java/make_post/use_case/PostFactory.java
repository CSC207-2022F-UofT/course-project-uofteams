package make_post.use_case;

import entities.Post;

import java.time.LocalDate;
import java.util.List;

public class PostFactory {
    public Post create(int poster, String title, String mainDesc, List<String> tags, String collaborators, LocalDate deadline,
                       int numPostsCreated){
        return new Post(poster, title, mainDesc, tags, collaborators, deadline, numPostsCreated);
    }
}
