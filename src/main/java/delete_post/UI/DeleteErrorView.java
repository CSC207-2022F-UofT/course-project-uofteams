package delete_post.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteErrorView extends JFrame implements ActionListener{

    Popup po;
    JFrame postView;
    PopupFactory pf;
    JPanel deleteError;

    DeleteErrorView(){
        this.postView = postView;
        pf = new PopupFactory();
        JLabel error = new JLabel("Cannot delete this post.");

        JButton close = new JButton("Close");
        close.addActionListener(this);

        deleteError = new JPanel();
        deleteError.add(error);
        deleteError.add(close);
    }

    public void actionPerformed(ActionEvent e){
        String d = e.getActionCommand();
        if (d.equals("Close")){
            po.hide();
            po = pf.getPopup(postView, deleteError, 180, 100);
        }
    }
}
