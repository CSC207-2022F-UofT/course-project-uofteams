package filter_post.use_case;

import entities.Post;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FilterPostInteractor implements FilterPostInputBoundary {
    private final FilterPostDsGateway postsGateway;
    private final FilterPostOutputBoundary filterPostPresenter;

    /**
     * Instantiate a FilterPostInteractor to run the use case.
     *
     * @param postsGateway        An abstraction to retrieve posts from the repository.
     * @param filterPostPresenter An abstraction to receive the filtered posts.
     */
    public FilterPostInteractor(FilterPostDsGateway postsGateway,
                                FilterPostOutputBoundary filterPostPresenter) {
        this.postsGateway = postsGateway;
        this.filterPostPresenter = filterPostPresenter;
    }

    /**
     * Filter the posts to be displayed by the tags given in filters and pass them to the output boundary.
     * @param filters The tags to filter the posts by.
     */
    @Override
    public void filterPosts(FilterPostRequestModel filters) {
        List<String> filterTags = filters.getFilterTags();
        List<Post> posts = postsGateway.getPosts();
        List<Post> postsWithTag = new ArrayList<>();

        if (filterTags.size() == 0) {
            // If there are no filters, return all the posts.
            postsWithTag = posts;
        } else {
            for (Post post : posts) {
                if (new HashSet<>(post.getTags()).containsAll(filterTags)) {
                    postsWithTag.add(post);
                }
            }
        }

        FilterPostResponseModel outputData = new FilterPostResponseModel(postsWithTag);

        filterPostPresenter.updateViewablePosts(outputData);
    }
}
