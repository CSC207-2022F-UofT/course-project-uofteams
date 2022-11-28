package make_post.ui;

import make_post.interface_adapters.MakePostController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakePostView extends JPanel implements ActionListener, PropertyChangeListener {
    private static String[] TAGS;
    private final MakePostController makePostController;
    private final JList<String> tagsList;
    private final JTextField enterTitle;
    private final JTextField enterCollaborators;
    private final JTextField enterMainDescription;
    private final JTextField enterDeadline;

    /**
     * This provides the view for making a Post. It has TextFields for entering all the information necessary for making
     * a Post.
     * @param presetTags These are the tags that will be used to filter the Posts.
     * @param makePostController The controller for the Make Post use case.
     */
    public MakePostView(String[] presetTags, MakePostController makePostController){
        this.TAGS = presetTags;
        this.makePostController = makePostController;
        this.tagsList = new JList<>(TAGS);
        this.enterTitle = new JTextField(1);
        this.enterCollaborators = new JTextField(4);
        this.enterMainDescription = new JTextField(10);
        this.enterDeadline = new JTextField(1);

        tagsList.setSelectionModel(new TagsListSelectionModel());
        JButton makePostButton = new JButton("Make Post");
        makePostButton.addActionListener(this);
        JScrollPane tagsScroller = new JScrollPane(tagsList);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        tagsScroller.setPreferredSize(new Dimension((int) width/8, (int) height/10));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Enter your post's title: ");
        this.add(titleLabel);
        this.add(enterTitle);
        JLabel collaboratorLabel = new JLabel("Describe your ideal collaborators: ");
        this.add(collaboratorLabel);
        this.add(enterCollaborators);
        JLabel tagsLabel = new JLabel("Select the appropriate tags for your post: ");
        this.add(tagsLabel);
        this.add(tagsScroller);
        JLabel mainDescLabel = new JLabel("Describe your project: ");
        this.add(mainDescLabel);
        this.add(enterMainDescription);
        JLabel deadlineLabel = new JLabel("Enter the date (YYYY-MM-DD) when your post should be deleted (max. six months): ");
        this.add(deadlineLabel);
        this.add(enterDeadline);
        this.add(makePostButton);
    }

    /**
     * Reacts to when the MakePost button is clicked. It then passes the information for making the post
     * to the controller via postBody.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedIndices = tagsList.getSelectedIndices();

        List<String> tags = new ArrayList<>();

        for (Integer i: selectedIndices) {
            tags.add(TAGS[i]);
        }

        String title = enterTitle.getText();
        String collaborators = enterCollaborators.getText();
        String mainDescription = enterMainDescription.getText();
        String deadline = enterDeadline.getText();
        Map<String, Object> postBody = new HashMap<>();
        postBody.put("title", title);
        postBody.put("collaborators", collaborators);
        postBody.put("mainDescription", mainDescription);
        postBody.put("deadline", deadline);
        postBody.put("tags", tags);
        makePostController.passToMakePostInteractor(postBody);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("creation failure")) {
            JFrame errorFrame = new JFrame("Error");
            switch ((String) evt.getNewValue()) {
                case ("Deadline more than 6 months away or in the past"):
                    JOptionPane.showMessageDialog(errorFrame, "Deadline more than 6 months away or in the past",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case ("Date is not in the correct format."):
                    JOptionPane.showMessageDialog(errorFrame, "Date is not in the correct format.",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

