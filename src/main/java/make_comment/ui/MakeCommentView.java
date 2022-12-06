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
    private final int postId;

    /**
     * This provides the view for making a comment. It has TextFields for entering all the information necessary for making
     * a comment.
     * @param postId The postId passed from viewing a post to making a comment.
     */


    public MakeCommentView(int postId, MakeCommentController controller){
        this.controller = controller;
        this.postId = postId;
        JButton addComment = new JButton("Add Comment");
        addComment.addActionListener(this);
        this.add(addComment);
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
                    "commentate", JOptionPane.QUESTION_MESSAGE);
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
            errorFrame.setVisible(true);
    }
        else {
            JFrame okFrame = new JFrame("Success");
            JOptionPane.showMessageDialog(okFrame, "your comment has been recorded.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            okFrame.setVisible(true);

        }
}
}
