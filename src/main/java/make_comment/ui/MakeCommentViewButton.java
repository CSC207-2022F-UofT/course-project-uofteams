package make_comment.ui;

import make_comment.interface_adapter.makeCommentController;
import make_comment.use_case.MakeCommentGateway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MakeCommentViewButton extends JPanel implements ActionListener ,PropertyChangeListener{
    private final makeCommentController controller;
    private final int postId;

    JButton addComment;

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
        if (evt.getPropertyName().equals("creation failure")) {
            JFrame errorFrame = new JFrame("Error");
            JOptionPane.showMessageDialog(errorFrame, "body was left blank.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            errorFrame.setVisible(true);

        }
    }
}
