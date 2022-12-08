package make_post;

import entities.CurrentUser;
import entities.User;
import make_post.drivers.MakePostDatabaseAccess;
import make_post.interface_adapters.MakePostController;
import make_post.interface_adapters.MakePostPresenter;
import make_post.interface_adapters.MakePostViewModel;
import make_post.ui.MakePostView;
import make_post.use_case.MakePostDsGateway;
import make_post.use_case.MakePostInputBoundary;
import make_post.use_case.MakePostInteractor;
import make_post.use_case.MakePostOutputBoundary;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        User currentUser = new User(false, 1, "test@mail.utoronto.ca", "test");
        CurrentUser.setCurrentUser(currentUser);
        MakePostViewModel viewModel = new MakePostViewModel();
        MakePostDsGateway dataAccess = new MakePostDatabaseAccess("src/main/java/database/");
        MakePostOutputBoundary presenter = new MakePostPresenter(viewModel);
        MakePostInputBoundary interactor = new MakePostInteractor(dataAccess, presenter);
        MakePostController makePostController = new MakePostController(interactor);
        MakePostView makePostView = new MakePostView(new String[]{"1", "2", "3"}, makePostController);
        viewModel.addObserver(makePostView);
        JFrame mainFrame = new JFrame();
        mainFrame.add(makePostView);
        mainFrame.setSize(400, 300);
        mainFrame.setVisible(true);

    }
}
