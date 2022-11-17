package favourite.ui;

import favourite.drivers.DataAccess;
import favourite.interface_adapters.FavouriteViewModel;
import favourite.interface_adapters.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FavouriteView implements View {
    private final FavouriteViewModel viewModel;
    private JButton favouriteButton = new JButton("Favourite");

    public FavouriteView(){
        this.viewModel = new FavouriteViewModel(new DataAccess(),this);
    }

    public void createScreen(Container pane){
        favouriteButton = new JButton("Favourite");
        favouriteButton.setBounds(10, 80, 80, 25);

    }

    public void update(){;}
    public void view(){
        favouriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.favourite();
            }
        });
    }

}
