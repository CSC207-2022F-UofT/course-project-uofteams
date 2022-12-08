package filter_post.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class FilterPostViewModel {
    // Using parallel arrays to store the post data.
    private String[] titles;
    private int[] ids;
    private String[] descriptions;
    private final PropertyChangeSupport observable;

    /**
     * Initialize the view model for the filter post use case.
     * @param titles        A list of titles of all the posts.
     * @param ids           A list of ids of all the posts.
     * @param descriptions  A list of descriptions of all the posts.
     */
    public FilterPostViewModel(String[] titles, int[] ids, String[] descriptions) {
        this.titles = titles;
        this.ids = ids;
        this.descriptions = descriptions;
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
     * Return a list of titles for the filtered posts.
     */
    public String[] getTitles() {
        return titles;
    }

    /**
     * Return a list of ids for the filtered posts.
     */
    public int[] getIds() {
        return ids;
    }

    /**
     * Return a list of descriptions for the filtered posts.
     */
    public String[] getDescriptions() {
        return descriptions;
    }

    /**
     * Update the fields of this view model.
     * @param titles        The new list of titles.
     * @param ids           The new list of post IDs.
     * @param descriptions  The new list of descriptions.
     */
    public void updateViewModel(String[] titles, int[] ids, String[] descriptions) {
        String[] oldTitles = this.titles;
        int[] oldIDs = this.ids;
        String[] oldDescriptions = this.descriptions;

        this.titles = titles;
        this.ids = ids;
        this.descriptions = descriptions;

        ArrayList<Object> results = new ArrayList<>();
        results.add(titles);
        results.add(ids);
        System.out.println("Arrived");
        System.out.println(results);
        observable.firePropertyChange("Search", null, results);
    }
}
