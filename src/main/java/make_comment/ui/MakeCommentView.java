package make_comment.ui;

import make_comment.interface_adapter.makeCommentController;
import make_comment.use_case.MakeCommentGateway;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MakeCommentView extends JPanel implements ActionListener, PropertyChangeListener {
    private final JTextField editBody = new JTextField("");
    private final makeCommentController controller;


    public MakeCommentView(makeCommentController controller){
        this.controller = controller;

        JLabel bodyLabel = new JLabel("input body of Comment");
        JButton confirm = new JButton("Post Comment");
        confirm.addActionListener(this);

        this.add(bodyLabel);
        this.add(editBody);
        this.add(confirm);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
