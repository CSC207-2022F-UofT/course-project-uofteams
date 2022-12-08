package log_out.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * view model which uses the Observable Design pattern to update the view
 */
public class LogOutViewModel {
    private final PropertyChangeSupport observable;

    /**
     * Initializes an instance of a LogOut View Model
     */
    public LogOutViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Adds new observers that observe changes to this class
     *
     * @param observer the observer to be added
     */
    public void addObserver(PropertyChangeListener observer){
        this.observable.addPropertyChangeListener("Log Out", observer);
    }

    /**
     * Update the observers in observable with the logOut success
     * @param presenterData the Presenter which contains the success or failure of logging out
     */
    public void updateViewModel(LogOutUserOutputData presenterData){
        observable.firePropertyChange("Log Out", "", presenterData.isSuccess());
    }
}
