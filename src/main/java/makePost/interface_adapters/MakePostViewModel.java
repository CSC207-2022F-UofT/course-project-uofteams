package makePost.interface_adapters;

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

    }

    public void updateViewModel() {

    }
}
