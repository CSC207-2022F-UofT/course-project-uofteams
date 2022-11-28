package make_comment.interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class makeCommentViewModel {
    private final PropertyChangeSupport observable;

    public makeCommentViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener observer) {
        this.observable.addPropertyChangeListener("creation success", observer);
        this.observable.addPropertyChangeListener("creation failure", observer);
    }

    public void updateViewModel(boolean creationSuccess, String errorMessage) {
        observable.firePropertyChange("creation success", false, creationSuccess);
        observable.firePropertyChange("creation failure", "", errorMessage);
    }
}