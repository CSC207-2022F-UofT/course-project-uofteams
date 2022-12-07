package make_comment.ui;

import make_comment.interface_adapter.MakeCommentController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class MakeCommentView extends JPanel implements ActionListener ,PropertyChangeListener{
    private final MakeCommentController controller;
    private int postId;

    /**
     * This provides the view for making a comment. It has TextFields for entering all the information necessary for making
     * a comment.
     * @param controller a MakeCommentController object
     */


    public MakeCommentView(MakeCommentController controller){
        this.controller = controller;
        this.postId = -1;
        JButton addComment = new JButton("Add Comment");
        addComment.addActionListener(this);
        this.add(addComment);
    }

    /**
     * Updates the postID instance variable so that MakeCommentView know which post it is on.
     * This method id called in the ViewPostView every time it is refreshed with a new post.
     *
     * @param postId the integer ID of the post that it is being displayed on
     */
    public void setPostID(int postId){
        this.postId = postId;
    }

    /**
     * Reacts to when the "Add Comment" button is clicked. It then passes the information for making the comment
     * to the controller via commentBody.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("commentate");
            String commentBody = JOptionPane.showInputDialog(frame, "Please enter the Comment",
                    "commentate", JOptionPane.PLAIN_MESSAGE);

            controller.passToInteractor(commentBody, this.postId);

    }

    /**
     * The view observes the view model. When a property change is fired, this method defines how to react.
     * When the post fails to be created, the appropriate error popup is shown. Upon creation success, the
     * window is closed and the main view is opened again and refreshed.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "creation failure")) {
            JFrame errorFrame = new JFrame("Error");
            JOptionPane.showMessageDialog(errorFrame, "body was left blank.",
                    "Error", JOptionPane.ERROR_MESSAGE);
    }
        else {
            JFrame okFrame = new JFrame("creation success");
            JOptionPane.showMessageDialog(okFrame, "your comment has been recorded.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

        }
}
}
