package filter_post.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class FilterPostViewModel {
    // Using parallel arrays to store the post data.
    private final PropertyChangeSupport observable;

    /**
     * Initialize the view model for the filter post use case.
     */
    public FilterPostViewModel() {
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Add a new observer to observe changes to this class.
     * @param observer a PostListView object which implements PropertyChangeListener
     */
    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener("Search", observer);
    }

    /**
     * Update the fields of this view model.
     * @param titles        The new list of titles.
     * @param ids           The new list of post IDs.
     */
    public void updateViewModel(String[] titles, int[] ids) {

        ArrayList<Object> results = new ArrayList<>();
        results.add(titles);
        results.add(ids);
        observable.firePropertyChange("Search", null, results);
    }
}
