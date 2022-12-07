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
     * Initializes ViewCommentViewModel.
     * @param bodys body of comments to be viewed
     * @param commentators commentators of comments to be viewed
     * @param creationDates creationDates of comments to be viewed
     */
    public ViewCommentViewModel(ArrayList<String> bodys, ArrayList<String> commentators, ArrayList<String> creationDates){
        this.bodys = bodys;
        this.commentators = commentators;
        this.creationDates = creationDates;
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Add a new observer to observe changes to this class.
     * @param observer a ViewCommentView object which implements PropertyChangeListener
     */
    public void addObserver(PropertyChangeListener observer) {
        this.observable.addPropertyChangeListener("success", observer);
        this.observable.addPropertyChangeListener("failure", observer);
    }

    /**
     * Fires a property change to trigger the propertyChange method in ViewCommentView
     * @param isReplies indicates if this post has replies.
     * @param errorMessage errormessage if use_case fails for whatever reason.
     * @param bodys body of comments to be viewed
     * @param commentators commentators of comments to be viewed
     * @param creationDates creationDates of comments to be viewed
     */
    public void updateViewModel(boolean isReplies, String errorMessage,
                                ArrayList<String> bodys, ArrayList<String> commentators, ArrayList<String> creationDates) {
        if (isReplies){
            this.bodys = bodys;
            this.commentators = commentators;
            this.creationDates = creationDates;
            ArrayList<ArrayList<String>> results = new ArrayList<>();
            results.add(this.bodys);
            results.add(this.commentators);
            results.add(this.creationDates);
            observable.firePropertyChange("success", false, results);
        } else {
            observable.firePropertyChange("failure", "", errorMessage);
        }





    }
}