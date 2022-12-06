package filter_post.use_case;

import java.util.ArrayList;
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
        String[] filterTags = filters.getFilterTags();
        List<String[]> posts = postsGateway.getPosts();
        List<String[]> postsWithTag = new ArrayList<>();

        for (String[] postInfo : posts) {
            String postTags = postInfo[4];
            if (containsAllWords(postTags, filterTags)) {
                // Formatting the needed post info into an array: {postID, title, body}.
                String[] postData = {postInfo[0], postInfo[2], postInfo[3]};
                postsWithTag.add(postData);
            }
        }

        String[][] output = new String[postsWithTag.size()][];

        for (int i = 0; i < postsWithTag.size(); i++) {
            output[i] = postsWithTag.get(i);
        }

        FilterPostResponseModel outputData = new FilterPostResponseModel(output);

        filterPostPresenter.updateViewablePosts(outputData);
    }

    /**
     * Return whether all the filter tags are in the tags string.
     * @param tags        The string representation of all the tags of this post.
     * @param filterTags  The tags the user wishes to filter with.
     */
    private static boolean containsAllWords(String tags, String[] filterTags) {
        for (String tag: filterTags) {
            if (!tags.contains(tag)) {
                return false;
            }
        }
        return true;
    }
}
