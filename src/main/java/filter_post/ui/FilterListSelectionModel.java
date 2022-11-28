package filter_post.ui;

import javax.swing.*;

/**
 * A data model for the list selection of tags.
 */
public class FilterListSelectionModel extends DefaultListSelectionModel {
    /**
     * Enables multi selection of elements by just clicking without ctrl + click.
     * @param start one end of the interval.
     * @param end other end of the interval
     */
    @Override
    public void setSelectionInterval(int start, int end) {
        if (start != end) {
            super.setSelectionInterval(start, end);
        } else if (isSelectedIndex(start)) {
            removeSelectionInterval(start, end);
        } else {
            addSelectionInterval(start, end);
        }
    }
}
