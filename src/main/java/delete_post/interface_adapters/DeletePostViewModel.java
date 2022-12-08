package delete_post.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeletePostViewModel {
    private final PropertyChangeSupport observable;

    /**
     * Initialize DeletePostViewModel object
     */
    public DeletePostViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Add observer to listen for changes in DeletePostViewModel
     * @param observer the observer
     */
    public void addObserver(PropertyChangeListener observer){
        this.observable.addPropertyChangeListener("success", observer);
        this.observable.addPropertyChangeListener("fail", observer);
    }

    /**
     * Triggers property change
     * @param success true if deletion was successful, false if failed
     * @param message Message to display in pop up frame
     */
    public void updateViewModel(boolean success, String message){
        this.observable.firePropertyChange("success", false, success);
        this.observable.firePropertyChange("fail", "", message);
    }
}
