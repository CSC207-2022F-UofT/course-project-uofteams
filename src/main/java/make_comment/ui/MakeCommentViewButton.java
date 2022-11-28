package make_comment.ui;

import make_comment.interface_adapter.makeCommentController;
import make_comment.use_case.MakeCommentGateway;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MakeCommentViewButton extends JPanel implements ActionListener, PropertyChangeListener {
    private final makeCommentController controller;


    public MakeCommentViewButton(makeCommentController controller){
        this.controller = controller;

        JLabel bodyLabel = new JLabel("input body of Comment");
        JButton addComment = new JButton("add Comment");
        addComment.addActionListener(this);

        this.add(addComment);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
