package delete_post.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeletePostViewModel {
    private final PropertyChangeSupport observable;

    public DeletePostViewModel(){
        this.observable = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener observer){
        this.observable.addPropertyChangeListener("success", observer);
        this.observable.addPropertyChangeListener("fail", observer);
    }

    public void updateViewModel(boolean success, boolean fail){
        this.observable.firePropertyChange("success", false, success);
        this.observable.firePropertyChange("fail", false, fail);
    }
}
