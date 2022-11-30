package view_post.ui;

import view_post.interface_adapters.ViewPostController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 */
public class PostListView extends JPanel implements PropertyChangeListener, ListSelectionListener {
    // JPanel that includes the scrollable list of posts a user can click to view
    private final JPanel postList;
    // the list of titles being displayed in the scrollable list
    private String[] titles;
    // the list of ids of the posts represented by the titles
    private int[] ids;
    // controller of this use case
    private final ViewPostController controller;
    //
    private JList list;

    /**
     * Initializes PostListView
     */
    public PostListView(ViewPostController controller){
        this.setPreferredSize(new Dimension(300, 680));
        // initializing instance variables
        this.postList = new JPanel();
        this.add(this.postList);
        // FilterPostBarView filterPostBar = new FilterPostBarView();
        // need to figure out whether FilterPostController should be an instance var / passed / called from main
        // this.postList.add(filterPostBar);
        this.titles = null;
        this.ids = null;
        this.controller = controller;
        this.list = null;

        // Default view when there are no posts to show
        JLabel noPostsText = new JLabel("No posts to show :(");
        this.postList.add(noPostsText);
    }

    /**
     * Updates the scrollable list of posts. When there are no posts to display, the default view is presented.
     * (Discalimer : this method depends on the indices of titles and ids to match exactly)
     */
    private void displayList(String[] titles, int[] ids){
        this.titles = titles;
        this.ids = ids;
        this.postList.removeAll();

        if (titles.length == 0){
            this.defaultDisplay();
        }else{
            JList titleList = new JList<>(this.titles);
            this.list = titleList;
            titleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            titleList.setLayoutOrientation(JList.VERTICAL);
            titleList.setVisibleRowCount(-1);
            JScrollPane scrollableList = new JScrollPane(this.list);
            scrollableList.setPreferredSize(new Dimension(300, 400));
            postList.add(scrollableList);
            postList.setBounds(0, 180, 300, 400);
        }
    }

    private void defaultDisplay(){
        JLabel noPostsText = new JLabel("No posts to show :(");
        this.postList.add(noPostsText);
    }

    /**
     * When a property change is fired in the FilterPost Use Case, PostListView responds by updating its list of posts
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("update".equals(evt.getPropertyName())){
            Object newData = evt.getNewValue();
//            String[] titles = newData.get(0);
//            int[] ids = newData.get(1);
//            this.displayList(titles, ids);
        }
    }

    /**
     * When an item is selected in the scrollable list, the ViewPost use case is triggered and displays the
     * post in the larger panel
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int index = this.list.getSelectedIndex();
        int postId = this.ids[index];
        controller.viewPost(postId);
    }
}
