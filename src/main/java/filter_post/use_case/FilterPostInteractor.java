package filter_post.use_case;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        List<String[]> postsWithTag = new ArrayList<>();
        List<String[]> posts = postsGateway.getPosts();

        ArrayList<String> countable1 = new ArrayList<>( Arrays.asList(filterTags) );
        if (countable1.isEmpty()){
            addPostsWithTag(filterTags, postsWithTag, posts);
        } else {
            if (Arrays.asList(filterTags).contains("Favourites")){
                postsWithTag = this.filterMyFavourites(postsWithTag, posts);
                // removing "Favourites" from filterTags
                List<String> list = new ArrayList<>(Arrays.asList(filterTags));
                list.remove("Favourites");
                filterTags = list.toArray(new String[0]);
            }
            if (Arrays.asList(filterTags).contains("MyPosts")){
                postsWithTag = this.filterMyPosts(postsWithTag, posts);
                // removing "MyPosts" from filterTags
                List<String> list = new ArrayList<>(Arrays.asList(filterTags));
                list.remove("MyPosts");
                filterTags = list.toArray(new String[0]);
            }
            ArrayList<String> countable2 = new ArrayList<>( Arrays.asList(filterTags) );
            if (!countable2.isEmpty()){
                addPostsWithTag(filterTags, postsWithTag, posts);
            }
        }

        String[][] output = new String[postsWithTag.size()][];

        for (int i = 0; i < postsWithTag.size(); i++) {
            output[i] = postsWithTag.get(i);
        }

        FilterPostResponseModel outputData = new FilterPostResponseModel(output);

        filterPostPresenter.updateViewablePosts(outputData);
    }

    private void addPostsWithTag(String[] filterTags, List<String[]> postsWithTag, List<String[]> posts) {
        for (String[] postInfo : posts) {
            String postTags = postInfo[4];
            if (containsAllWords(postTags, filterTags)) {
                // Formatting the needed post info into an array: {postID, title, body}.
                String[] postData = {postInfo[0], postInfo[2], postInfo[3]};
                // Making sure this post has not been added to the return list
                boolean postInArray = false;
                for (String[] post : postsWithTag){
                    if (Objects.equals(postData[0], post[0])) {
                        postInArray = true;
                        break;
                    }
                }
                if (!postInArray){
                    postsWithTag.add(postData);
                }
            }
        }
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

    /**
     * Pulls all the relevant data for the posts in the current user's favourites list.
     */
    private List<String[]> filterMyFavourites(List<String[]> postsWithTag,
                                              List<String[]> posts){
        // getting list of IDs of the posts the current user has favourited
        List<Integer> myFavourites = postsGateway.getFavourites();

        // adding the post info to postsWithTag
        return personalTargetFilter(postsWithTag, posts, myFavourites);
    }

    private List<String[]> personalTargetFilter(List<String[]> postsWithTag, List<String[]> posts,
                                                List<Integer> targetIds) {
        for (String[] postInfo: posts) {
            int postId = Integer.parseInt(postInfo[0]);
            if (targetIds.contains(postId)) {
                // Formatting the needed post info into an array: {postID, title, body}.
                String[] postData = {postInfo[0], postInfo[2], postInfo[3]};
                // Making sure this post has not been added to the return list
                boolean postInArray = false;
                for (String[] post : postsWithTag){
                    if (Objects.equals(postData[0], post[0])) {
                        postInArray = true;
                        break;
                    }
                }
                if (!postInArray){
                    postsWithTag.add(postData);
                }
            }
        }
        return postsWithTag;
    }

    /**
     * Pulls all the relevant data for the posts in the current user's favourites list.
     */
    private List<String[]> filterMyPosts(List<String[]> postsWithTag,
                                         List<String[]> posts){
        // getting list of IDs of the posts the current user has made
        List<Integer> myPosts = postsGateway.getMyPosts();

        // adding the post info to postsWithTag
        return personalTargetFilter(postsWithTag, posts, myPosts);
    }
}
