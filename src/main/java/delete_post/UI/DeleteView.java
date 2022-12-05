package delete_post.UI;

import delete_post.interface_adapters.DeletePostController;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class DeleteView extends JPanel implements ActionListener, PropertyChangeListener{

    private final DeletePostController controller;
    int postId;

    public DeleteView(int postId, DeletePostController controller){
        this.controller = controller;
        this.postId = postId;
        JButton deleteButton = new JButton("Delete");
        this.add(deleteButton);
        deleteButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        controller.delete(this.postId, false);
    }

    public void propertyChange(PropertyChangeEvent event){
        JFrame statusFrame = new JFrame();
        statusFrame.setVisible(true);
        statusFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        if (event.getPropertyName().equals("success")){
            JOptionPane.showMessageDialog(statusFrame,"Post Deleted");
        }
        else if (event.getPropertyName().equals("fail")){
            JOptionPane.showMessageDialog(statusFrame, "Cannot Delete Post");
        }
    }
}
