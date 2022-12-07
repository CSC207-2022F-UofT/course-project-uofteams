package delete_post.UI;

import delete_post.interface_adapters.DeletePostController;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class DeleteView extends JPanel implements ActionListener, PropertyChangeListener{

    private final DeletePostController controller;
    int postId;

    public DeleteView(DeletePostController controller){
        this.controller = controller;
        this.postId = -1;
        JButton deleteButton = new JButton("Delete");
        this.add(deleteButton);
        deleteButton.addActionListener(this);
    }

    /**
     * Updates the postID instance variable so that DeleteView knows which post it is on.
     * This method id called in the ViewPostView every time it is refreshed with a new post.
     *
     * @param id the integer ID of the post that it is being displayed on
     */
    public void setPostId(int id) {
        this.postId = id;
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
        else if (event.getNewValue().equals("null")){
            JOptionPane.showMessageDialog(statusFrame, "Post has already been deleted", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (event.getNewValue().equals("permission")){
            JOptionPane.showMessageDialog(statusFrame, "You do not have permission to delete this Post", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
