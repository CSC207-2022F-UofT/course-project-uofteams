package filter_post.interface_adapters;

import entities.Post;
import filter_post.use_case.FilterPostOutputBoundary;
import filter_post.use_case.FilterPostResponseModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * An observable presenter for this use case with a list of viewable posts.
 */
public class FilterPostPresenter implements FilterPostOutputBoundary {
    private List<Post> viewablePosts;
    private final PropertyChangeSupport observable;

    /**
     * Instantiate a FilterPostPresenter object.
     * @param viewablePosts A list of posts to be displayed.
     */
    public FilterPostPresenter(List<Post> viewablePosts) {
        this.viewablePosts = viewablePosts;
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Add a new observer to observe changes to this class.
     * @param observer The observer to be observing this observable.
     */
    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener("viewable posts", observer);
    }

    /**
     * Update the viewable posts and notify any observers.
     * @param filteredPosts A FilterPostResponseModel object storing the filtered posts.
     */
    @Override
    public void updateViewablePosts(FilterPostResponseModel filteredPosts) {
        List<Post> previousPosts = this.viewablePosts;
        List<Post> newPosts = filteredPosts.getFilteredPosts();
        this.viewablePosts = newPosts;
        observable.firePropertyChange("viewable posts", previousPosts, newPosts);
    }
}
