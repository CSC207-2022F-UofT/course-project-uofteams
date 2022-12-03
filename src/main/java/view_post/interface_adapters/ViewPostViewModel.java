package view_post.interface_adapters;

import view_post.ui.ViewPostView;

import java.beans.PropertyChangeSupport;

/**
 * The view model class of the View Post use case.
 */
public class ViewPostViewModel {
    private final PropertyChangeSupport support;
    private final ViewPostView view;

    /**
     * Initializes ViewPostViewModel.
     * @param view A ViewPostView object
     */
    public ViewPostViewModel(ViewPostView view){
        this.support = new PropertyChangeSupport(this);
        this.view = view;
    }

    /**
     * Fires a property change to trigger the propertyChange method in ViewPostView
     * @param data A ViewPostOutputData object that contains the data of the post that we are trying to display
     */
    public void updateView(ViewPostOutputData data){
        support.firePropertyChange("show post", null, data);
    }

}
