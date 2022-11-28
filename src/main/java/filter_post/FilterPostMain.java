package filter_post;

import filter_post.drivers.FilterPostDataAccess;
import filter_post.interface_adapters.FilterPostController;
import filter_post.ui.FilterPostBarView;
import filter_post.use_case.*;

import javax.swing.*;

/**
 * DO NOT USE ANYWHERE IN PROGRAM.
 * THIS IS JUST FOR TESTING THE UI.
 */
public class FilterPostMain {
    public static void main(String[] args) {
        final String[] TAGS = {"0", "1", "2", "3", "4", "TEST", "CSC", "207", "PAUL", "MOOGAH"};
        JFrame frame = new JFrame();

        FilterPostDsGateway dsGateway = new FilterPostDataAccess("src/test/java/filter_post/posts.csv");
        FilterPostOutputBoundary presenter = new FilterPostOutputBoundary() {
            @Override
            public void updateViewablePosts(FilterPostResponseModel filteredPosts) {
                StringBuilder output = new StringBuilder();
                String[][] posts = filteredPosts.getFilteredPosts();

                for (String[] post: posts) {
                    output.append(String.join(" ", post));
                    output.append("\n");
                }

                JOptionPane.showMessageDialog(frame, output);
            }
        };
        FilterPostInputBoundary interactor = new FilterPostInteractor(dsGateway, presenter);
        FilterPostController controller = new FilterPostController(interactor);
        FilterPostBarView filterPostBarView = new FilterPostBarView(TAGS, controller);

        frame.add(filterPostBarView);

        frame.pack();
        frame.setVisible(true);
    }
}
