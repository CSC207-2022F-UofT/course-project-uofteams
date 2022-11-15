package filter_post.drivers;

import entities.Post;
import filter_post.use_case.FilterPostDsGateway;

import java.util.ArrayList;
import java.util.List;

public class DatabasePost implements FilterPostDsGateway {
    @Override
    public List<Post> getPosts() {
        return new ArrayList<>();
    }
}
