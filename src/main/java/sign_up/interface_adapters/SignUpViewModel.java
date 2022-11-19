package sign_up.interface_adapters;

import sign_up.use_case.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/*
* The view model, which implements the observable design pattern to update the view
* */
public class SignUpViewModel {
    private final PropertyChangeSupport observable;

    /*
    * Initialize an instance of Sign up View Model
    * */
    public SignUpViewModel() {
        this.observable = new PropertyChangeSupport(this);
    }

    /*
    * Add a new observer which observes changes in this class
    *
    * @param observer The observer to be added
    * */
    public void addObserver(PropertyChangeListener observer) {
        this.observable.addPropertyChangeListener("creation success", observer);
        this.observable.addPropertyChangeListener("creation failure", observer);
    }

    /*
    * Update the observers in observable with creation status and possible error
    *
    * @param outputData The SignUpUserOutputData which contains success or failure of signing up
    * */
    public void updateViewModel(SignUpUserOutputData outputData) {
        observable.firePropertyChange("creation success", false, outputData.isCreationSuccess());
        observable.firePropertyChange("creation failure", "", outputData.getErrorMessage());
    }
}
