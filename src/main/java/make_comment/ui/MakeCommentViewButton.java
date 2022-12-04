package make_comment.ui;

import make_comment.interface_adapter.makeCommentController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class MakeCommentViewButton extends JPanel implements ActionListener ,PropertyChangeListener{
    private final makeCommentController controller;
    private final int postId;


    public MakeCommentViewButton(int postId, makeCommentController controller){
        this.controller = controller;
        this.postId = postId;
        JButton addComment = new JButton("add Comment");
        addComment.addActionListener(this);
        this.add(addComment);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("commentate");
            String commentBody = JOptionPane.showInputDialog(frame, "Please enter the Comment",
                    "commentate", JOptionPane.QUESTION_MESSAGE);
            controller.passToInteractor(commentBody, this.postId);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "creation failure")) {
            JFrame errorFrame = new JFrame("Error");
            JOptionPane.showMessageDialog(errorFrame, "body was left blank.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            errorFrame.setVisible(false);
    }
        else {
            JFrame okFrame = new JFrame("Success");
            JOptionPane.showMessageDialog(okFrame, "your comment has been record.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            okFrame.setVisible(false);

        }
}
}
