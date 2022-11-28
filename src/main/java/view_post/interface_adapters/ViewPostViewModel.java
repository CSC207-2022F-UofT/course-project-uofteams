package view_post.interface_adapters;

import view_post.ui.ViewPostView;

import java.beans.PropertyChangeSupport;

public class ViewPostViewModel {
    private final PropertyChangeSupport support;
    private final ViewPostView view;

    public ViewPostViewModel(ViewPostView view){
        this.support = new PropertyChangeSupport(this);
        this.view = view;
    }

    public void updateView(ViewPostOutputData data){
        view.setData(data);
        support.firePropertyChange("show post", null, data);
    }

}
