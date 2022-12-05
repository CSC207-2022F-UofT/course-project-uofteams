package view_comment.ui;
import view_comment.interface_adapters.ViewCommentController;

import javax.swing.*;
import java.awt.*;
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
            ArrayList<ArrayList<String>> commentArrayList = (ArrayList<ArrayList<String>>) evt.getNewValue();
            JPanel rootPanel = new JPanel();

            rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
            GridBagConstraints c = new GridBagConstraints();
            ArrayList<ArrayList<String>> attribute = (ArrayList<ArrayList<String>>) evt.getNewValue();
            for (int x = 0; x < attribute.get(0).size(); x++){
                JPanel tempPanel = new JPanel();
                tempPanel.setLayout(new GridBagLayout());

                JLabel commentatorLabel = new JLabel(attribute.get(1).get(x));
                c.gridx = 0;
                c.gridy = 0;
                c.weightx = 0.5;
                c.fill = GridBagConstraints.HORIZONTAL;
                tempPanel.add(commentatorLabel, c);

                JLabel bodyLabel = new JLabel(attribute.get(0).get(x));
                c.fill = GridBagConstraints.HORIZONTAL;
                c.ipady = 40;
                c.weightx = 0.0;
                c.gridwidth = 3;
                c.gridx = 0;
                c.gridy = 1;
                tempPanel.add(bodyLabel, c);

                JLabel creationDateLabel = new JLabel(attribute.get(2).get(x));
                c.fill = GridBagConstraints.HORIZONTAL;
                c.ipady = 0;
                c.anchor = GridBagConstraints.PAGE_END;
                c.insets = new Insets(10,0,0,0);
                c.gridx = 1;
                c.gridwidth = 2;
                c.gridy = 2;
                tempPanel.add(creationDateLabel, c);
                rootPanel.add(tempPanel);
            };
            JScrollPane scroller = new JScrollPane(rootPanel);
            frame.add(scroller);




        }

    }
}
