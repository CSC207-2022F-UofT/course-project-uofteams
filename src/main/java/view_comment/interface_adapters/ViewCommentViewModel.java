package view_comment.interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ViewCommentViewModel {

    private final PropertyChangeSupport observable;
    private ArrayList<String> bodys;
    private ArrayList<String> commentators;
    private ArrayList<String> creationDates;
    /**
     * Initialize the view model for the filter post use case.
     * @param bodys        A list of body for each comment
     * @param commentators           list of user of each comment
     * @param creationDates  A list creation date of each comment
     */
    public ViewCommentViewModel(ArrayList<String> bodys, ArrayList<String> commentators, ArrayList<String> creationDates){
        this.bodys = bodys;
        this.commentators = commentators;
        this.creationDates = creationDates;
        this.observable = new PropertyChangeSupport(this);
    }

    public ArrayList<String> getCommentators() {
        return commentators;
    }

    public ArrayList<String> getBodys() {
        return bodys;
    }

    public ArrayList<String> getCreationDates() {
        return creationDates;
    }

    public void addObserver(PropertyChangeListener observer) {
        this.observable.addPropertyChangeListener("success", observer);
        this.observable.addPropertyChangeListener("failure", observer);
    }

    public void updateViewModel(boolean creationSuccess, String errorMessage,
                                ArrayList<String> bodys, ArrayList<String> commentators, ArrayList<String> creationDates) {
        this.bodys = bodys;
        this.commentators = commentators;
        this.creationDates = creationDates;
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        results.add(this.bodys);
        results.add(this.commentators);
        results.add(this.creationDates);
        observable.firePropertyChange("success", false, results);
        observable.firePropertyChange("failure", "", errorMessage);


    }
}
