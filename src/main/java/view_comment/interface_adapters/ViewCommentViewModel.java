package view_comment.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewCommentViewModel {

    private final PropertyChangeSupport observable;
    private String[] bodys;
    private String[] commentators;
    private String[] creationDates;
    /**
     * Initialize the view model for the filter post use case.
     * @param bodys        A list of body for each comment
     * @param commentators           list of user of each comment
     * @param creationDates  A list creation date of each comment
     */
    public ViewCommentViewModel(String[] bodys, String[] commentators, String[] creationDates){
        this.bodys = bodys;
        this.commentators = commentators;
        this.creationDates = creationDates;
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
