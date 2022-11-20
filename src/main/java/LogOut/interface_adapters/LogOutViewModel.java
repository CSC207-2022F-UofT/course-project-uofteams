package LogOut.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogOutViewModel {
    private final PropertyChangeSupport observable;

    public LogOutViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener observer){
        this.observable.addPropertyChangeListener("Log Out", observer);
    }

    public void updateViewModel(LogOutPresenter presenter){
        observable.firePropertyChange("Log Out", "", presenter.isLogOutSuccess());
    }
}
