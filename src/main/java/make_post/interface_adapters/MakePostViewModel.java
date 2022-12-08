package make_post.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MakePostViewModel {
    private final PropertyChangeSupport observable;

    /**
     * Initialise this ViewModel for the MakePost use case.
     */
    public MakePostViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Add a new observer to observe changes to this class.
     * @param observer The observer to be observing this observable.
     */
    public void addObserver(PropertyChangeListener observer) {
        this.observable.addPropertyChangeListener("creation success", observer);
        this.observable.addPropertyChangeListener("creation failure", observer);
    }

    /**
     * Fires property changes, which the view detects and responds to.
     * @param creationSuccess boolean indicating whether creation of post was a success.
     * @param errorMessage corresponding error message.
     */
    public void updateViewModel(boolean creationSuccess, String errorMessage) {
        observable.firePropertyChange("creation success", false, creationSuccess);
        observable.firePropertyChange("creation failure", "", errorMessage);
    }
}
