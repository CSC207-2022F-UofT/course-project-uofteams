package make_comment.interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class makeCommentViewModel {
    private final PropertyChangeSupport observable;

    public makeCommentViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener observer){
        this.observable.addPropertyChangeListener("Log Out", observer);
    }

    public void updateViewModel(LogOutPresenterData presenterData){
        observable.firePropertyChange("Log Out", "", presenterData.isSuccess());
    }
}