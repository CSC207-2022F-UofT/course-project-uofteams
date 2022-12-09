package log_out.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View Model for the LogOut use case
 */
public class LogOutViewModel {
    private final PropertyChangeSupport observable;

    /**
     * Initialize a LogOutViewModel
     */
    public LogOutViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Add a property change Listener to observe the LogOutViewModel
     * @param observer the observer which will observe the LogOutViewModel
     */
    public void addObserver(PropertyChangeListener observer){
        this.observable.addPropertyChangeListener("Log Out", observer);
    }

    /**
     * update observers with a LogOut's success
     * @param presenterData the data which contains if a LogOut was successful or not
     */
    public void updateViewModel(LogOutUserOutputData presenterData){
        observable.firePropertyChange("Log Out", "", presenterData.isSuccess());
    }
}
