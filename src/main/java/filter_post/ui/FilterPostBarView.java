package filter_post.ui;

import filter_post.interface_adapters.FilterPostController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterPostBarView extends JPanel implements ActionListener{
    public final String[] TAGS;
    private final FilterPostController filterPostController;
    private final JList<String> list;
    private final JTextArea activeFilters;

    /**
     * A part of the main view with a multi-select list and button to filter posts.
     * @param presetTags            The predefined tags that the user can filter by.
     * @param filterPostController  The controller for the use case.
     */
    public FilterPostBarView(String[] presetTags, FilterPostController filterPostController) {
        this.TAGS = presetTags;
        this.filterPostController = filterPostController;
        this.list = new JList<>(TAGS);
        this.activeFilters = new JTextArea("None");

        list.setSelectionModel(new FilterListSelectionModel());
        activeFilters.setLineWrap(true);
        activeFilters.setWrapStyleWord(true);
        activeFilters.setEditable(false);

        JButton filterButton = new JButton("Filter!");
        filterButton.addActionListener(this);

        JPanel textArea = new JPanel();
        JLabel text = new JLabel("Active filters:");
        textArea.setLayout(new BoxLayout(textArea, BoxLayout.Y_AXIS));
        textArea.add(text);
        textArea.add(activeFilters);

        JScrollPane filterScroller = new JScrollPane(list);
        filterScroller.setPreferredSize(new Dimension(100, 80));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(textArea);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(filterScroller);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(filterButton);
    }

    /**
     * React to a button click and start the use case for filtering posts.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedIndices = list.getSelectedIndices();
        String[] filterTags = new String[selectedIndices.length];

        for (int i = 0; i < selectedIndices.length; i++) {
            filterTags[i] = TAGS[selectedIndices[i]];
        }

        if (filterTags.length != 0) {
            activeFilters.setText(String.join(", ", filterTags));
        } else {
            activeFilters.setText("None");
        }

        filterPostController.filter(filterTags);
    }
}
