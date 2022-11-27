package favourite.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * FavouriteViewModel in the interface adapters layer implements the FavouriteOutputBoundary interface.
 * It runs the use case and interacts with the use case layer in response to a user's interaction with FavouriteView.
 */
public class FavouriteViewModel {
    private final PropertyChangeSupport observable;

    public FavouriteViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Add a new observer to observe changes to this class.
     * @param observer The observer to be observing this observable.
     */
    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener("favourited", observer);
        observable.addPropertyChangeListener("unfavourited", observer);
    }

    public void updateViewModel(boolean favourited, boolean unfavourited) {
        observable.firePropertyChange("favourited", false, favourited);
        observable.firePropertyChange("unfavourited", false, unfavourited);
    }

}
