package makePost.ui;

import makePost.interface_adapters.MakePostController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakePostView extends JPanel implements ActionListener{
    public static String[] TAGS;
    private final MakePostController makePostController;
    private final JList<String> tagsList;
    private final JTextField enterTitle;
    private final JTextField enterCollaborators;
    private final JTextField enterMainDescription;
    private final JTextField enterDeadline;

    public MakePostView(String[] presetTags, MakePostController makePostController){
        this.TAGS = new String[]{"Sports", "Tech", "Startups"};
        this.makePostController = makePostController;
        this.tagsList = new JList<>(TAGS);
        this.enterTitle = new JTextField(1);
        this.enterCollaborators = new JTextField(4);
        this.enterMainDescription = new JTextField(10);
        this.enterDeadline = new JTextField(1);

        tagsList.setSelectionModel(new TagsListSelectionModel());
        JButton makePostButton = new JButton("Make Post");
        makePostButton.addActionListener(this);
        JPanel makePostArea = new JPanel();
        JScrollPane tagsScroller = new JScrollPane(tagsList);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        tagsScroller.setPreferredSize(new Dimension(100, 80));

        makePostArea.setLayout(new BoxLayout(makePostArea, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Enter your post's title: ");
        makePostArea.add(titleLabel);
        makePostArea.add(enterTitle);
        JLabel collaboratorLabel = new JLabel("Describe your ideal collaborators: ");
        makePostArea.add(collaboratorLabel);
        makePostArea.add(enterCollaborators);
        JLabel tagsLabel = new JLabel("Select the appropriate tags for your post: ");
        makePostArea.add(tagsLabel);
        makePostArea.add(tagsScroller);
        JLabel mainDescLabel = new JLabel("Describe your project: ");
        makePostArea.add(mainDescLabel);
        makePostArea.add(enterMainDescription);
        JLabel deadlineLabel = new JLabel("Enter the date (YYYY-MM-DD) when your post should be deleted (max. six months): ");
        makePostArea.add(deadlineLabel);
        makePostArea.add(enterDeadline);
        makePostArea.add(makePostButton);

        JFrame frame = new JFrame();
        frame.add(makePostArea);
        frame.setSize((int) (3 * width/4), (int) (3 * height/4));
        frame.setVisible(true);
    }

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
        makePostController.passToInteractor(postBody);
    }
}
