package view_post;

import filter_post.drivers.FilterPostDataAccess;
import filter_post.interface_adapters.FilterPostController;
import filter_post.interface_adapters.FilterPostPresenter;
import filter_post.interface_adapters.FilterPostViewModel;
import filter_post.ui.FilterPostBarView;
import filter_post.use_case.FilterPostInteractor;
import view_post.drivers.ViewPostDatabaseAccess;
import view_post.interface_adapters.ViewPostController;
import view_post.interface_adapters.ViewPostOutputData;
import view_post.interface_adapters.ViewPostPresenter;
import view_post.interface_adapters.ViewPostViewModel;
import view_post.ui.HeaderView;
import view_post.ui.MainPostView;
import view_post.ui.PostListView;
import view_post.ui.ViewPostView;
import view_post.use_case.ViewPostInteractor;

import javax.swing.*;

public class ViewPostMain {
    public static void main(String[] arg){
        final String[] TAGS = {"0", "1", "2", "3", "4", "TEST", "CSC", "207", "PAUL", "MOOGAH"};

        String[] titles = {};
        int[] ids = {};
        String[] desc = {};
        FilterPostViewModel fpViewModel = new FilterPostViewModel(titles, ids, desc);
        FilterPostPresenter fpPresenter = new FilterPostPresenter(fpViewModel);
        FilterPostDataAccess fpDataAccess = new FilterPostDataAccess("src/main/java/database");
        FilterPostInteractor fpInteractor = new FilterPostInteractor(fpDataAccess, fpPresenter);
        FilterPostController fpController = new FilterPostController(fpInteractor);
        FilterPostBarView filterBar = new FilterPostBarView(TAGS, fpController);


        ViewPostView viewPost = new ViewPostView();
        ViewPostViewModel viewModel = new ViewPostViewModel(viewPost);
        ViewPostPresenter presenter = new ViewPostPresenter(viewModel);
        ViewPostDatabaseAccess gateway = new ViewPostDatabaseAccess("src/main/java/database");
        ViewPostInteractor interactor = new ViewPostInteractor(gateway, presenter);
        ViewPostController controller = new ViewPostController(interactor);
        PostListView postList = new PostListView(controller, filterBar);
        HeaderView header = new HeaderView("src/main/resources/");

        MainPostView collectivePanel = new MainPostView(viewPost, postList, header);

        JFrame frame = new JFrame("main view test");
        frame.setVisible(true);
        frame.setSize(900,800);
        frame.add(collectivePanel.getMainPostView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String body = "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool" +
                "a lot of text about how this project is so cool a lot of text about how this project is so cool";

        ViewPostOutputData testData = new ViewPostOutputData("email@mail.utoronto.ca",
                body, "", "2022-12-25", "2022-11-25",
                "", 1, "title title TITLE!");

        // need to change public/private access
        viewPost.displayPost(testData);

        String[] titles2 = {"project1", "project2", "project3", "project1", "project2", "project3",
                "project1", "project2", "project3", "project1", "project2", "project3", "project1", "project2",
                "project3", "project1", "project2", "project3", "project1", "project2",
                "project3", "project1", "project2", "project3"};
        int[] ids2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        // need to change public/private access
        postList.displayList(titles2, ids2);
    }
}

