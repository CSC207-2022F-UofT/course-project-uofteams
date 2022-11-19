package login.interface_adapters;

import login.use_case.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * view model which uses the Observable Design pattern to update the view
 */
public class LogInViewModel  {
    private final PropertyChangeSupport observable;

    /**
     * Initializes an instance of a Log In View Model
     */
    public LogInViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Adds new observers that observe changes to this class
     *
     * @param observer the observer to be added
     */
    public void addObserver(PropertyChangeListener observer){
        this.observable.addPropertyChangeListener("Login Success", observer);
        this.observable.addPropertyChangeListener("Login Failure", observer);
    }

    /**
     * Update the observers in observable with the log in success and possible error messages
     * @param presenter the Presenter which contains the success or failure of logging in
     */
    public void updateViewModel(LogInPresenter presenter){
        observable.firePropertyChange("Login Success", false, presenter.isLogInSuccess());
        observable.firePropertyChange("Login Failure", "", presenter.getErrorMessage());
    }

}



