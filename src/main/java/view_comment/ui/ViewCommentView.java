package view_comment.ui;
import view_comment.interface_adapters.ViewCommentController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewCommentView extends JPanel implements ActionListener ,PropertyChangeListener{
    private final ViewCommentController controller;
    private final int postId;

    public ViewCommentView(int postId, ViewCommentController controller) {
        this.controller = controller;
        this.postId = postId;
        JButton viewComment = new JButton("View comments");
        viewComment.addActionListener(this);
        this.add(viewComment);
        controller.passToInteractor(postId);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Comments");
        JList commentList = new JList();



    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
