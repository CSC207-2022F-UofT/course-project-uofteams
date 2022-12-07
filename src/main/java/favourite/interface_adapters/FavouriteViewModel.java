package favourite.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model class of the Favourite use case
 */
public class FavouriteViewModel {
    private final PropertyChangeSupport observable;

    /**
     * Initializes FavouriteViewModel
     */
    public FavouriteViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Add a new observer to observe changes to this class.
     * @param observer a FavouriteView object that implements PropertyChangeListener
     */
    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener("favourited", observer);
        observable.addPropertyChangeListener("unfavourited", observer);
    }

    /**
     * Triggers PropertyChange based on whether the post was "favourited" or "unfavourited"
     * @param favourited boolean that is true if the post was "favourited"
     * @param unfavourited boolean that is true if the post was "unfavourited"
     */
    public void updateViewModel(boolean favourited, boolean unfavourited) {
        observable.firePropertyChange("favourited", false, favourited);
        observable.firePropertyChange("unfavourited", false, unfavourited);
    }

}
