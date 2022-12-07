package view_post.interface_adapters;

import view_post.ui.ViewPostView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model class of the View Post use case.
 */
public class ViewPostViewModel {
    private final PropertyChangeSupport observable;
    private final ViewPostView view;

    /**
     * Initializes ViewPostViewModel.
     * @param view A ViewPostView object
     */
    public ViewPostViewModel(ViewPostView view){
        this.observable = new PropertyChangeSupport(this);
        this.view = view;
    }

    /**
     * Add a new observer to observe changes to this class.
     * @param observer a ViewPostView object which implements PropertyChangeListener
     */
    public void addObserver(PropertyChangeListener observer){
        this.observable.addPropertyChangeListener("show post", observer);
    }

    /**
     * Fires a property change to trigger the propertyChange method in ViewPostView
     * @param data A ViewPostOutputData object that contains the data of the post that we are trying to display
     */
    public void updateView(ViewPostOutputData data){
        if(data.getPostID() == -1){
            observable.firePropertyChange("show error", null, "This post does not exist.");
        }
        else{
            observable.firePropertyChange("show post", null, data);
        }
    }

}
