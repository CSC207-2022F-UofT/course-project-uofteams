package sign_up.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Thw View Model for the sign-up use case, which manipulates the view to show success or failure
 */
public class SignUpViewModel {
    private final PropertyChangeSupport observable;

    /**
     * Initialize a SignUpViewModel
     */
    public SignUpViewModel() {
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Add a PropertyChangeListener to observe the SignUpViewModel
     * @param observer the observer which will observe the SignUpViewModel
     */
    public void addObserver(PropertyChangeListener observer) {
        this.observable.addPropertyChangeListener("creation success", observer);
        this.observable.addPropertyChangeListener("creation failure", observer);
    }

    /**
    * Update the observers in observable with creation status and possible error
    * @param outputData The SignUpUserOutputData which contains success or failure of signing up
    */
    public void updateViewModel(SignUpUserOutputData outputData) {
        observable.firePropertyChange("creation success", false, outputData.isCreationSuccess());
        observable.firePropertyChange("creation failure", "", outputData.getErrorMessage());
    }
}
