package filter_post.interface_adapters;

import entities.Post;
import filter_post.use_case.FilterPostOutputBoundary;
import filter_post.use_case.FilterPostResponseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The presenter for this use case that stores the view model.
 */
public class FilterPostPresenter implements FilterPostOutputBoundary {
    private final FilterPostViewModel viewModel;

    /**
     * Instantiate a FilterPostPresenter object.
     * @param viewModel A FilterPostViewModel object to act as the bridge between the view and presenter.
     */
    public FilterPostPresenter(FilterPostViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Update the viewable posts and notify any observers.
     * @param responseModel A FilterPostResponseModel object storing the filtered posts.
     */
    @Override
    public void updateViewablePosts(FilterPostResponseModel responseModel) {
        List<Post> filteredPosts = responseModel.getFilteredPosts();

        List<String> tempTitles = new ArrayList<>();
        List<Integer> tempIDs = new ArrayList<>();
        List<String> tempDescriptions = new ArrayList<>();

        for (Post post: filteredPosts) {
            tempTitles.add(post.getTitle());
            tempIDs.add(post.getID());
            tempDescriptions.add(post.getBody());
        }

        String[] newTitles = tempTitles.toArray(new String[0]);
        int[] newIDs = new int[tempIDs.size()];
        String[] newDescriptions = tempDescriptions.toArray(new String[0]);

        for (int i = 0; i < tempIDs.size(); i++) {
            newIDs[i] = tempIDs.get(i);
        }

        viewModel.updateViewModel(newTitles, newIDs, newDescriptions);
    }
}
