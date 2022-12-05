package view_comment.ui;
import view_comment.interface_adapters.ViewCommentController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;

public class ViewCommentView extends JPanel implements ActionListener ,PropertyChangeListener{
    private final ViewCommentController controller;
    private final int postId;

    public ViewCommentView(int postId, ViewCommentController controller) {
        this.controller = controller;
        this.postId = postId;
        JButton viewComment = new JButton("View comments");
        viewComment.addActionListener(this);
        this.add(viewComment);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.passToInteractor(postId);




    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "failure")){
            JFrame errorFrame = new JFrame("Error");
            JOptionPane.showMessageDialog(errorFrame, "This post currently have no comment.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            errorFrame.setVisible(false);

        } else {
            JFrame frame = new JFrame("Comments");
            JList commentList = new JList();
            ArrayList<Objects> commentArrayList = evt.getNewValue();
            for ()

        }

    }
}
