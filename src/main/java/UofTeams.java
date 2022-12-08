import delete_post.UI.DeleteView;
import delete_post.drivers.DeletePostDataAccess;
import delete_post.interface_adapters.DeletePostController;
import delete_post.interface_adapters.DeletePostPresenter;
import delete_post.interface_adapters.DeletePostViewModel;
import delete_post.use_case.*;
import favourite.drivers.FavouriteDatabaseAccess;
import favourite.interface_adapters.FavouriteController;
import favourite.interface_adapters.FavouritePresenter;
import favourite.interface_adapters.FavouriteViewModel;
import favourite.ui.FavouriteView;
import favourite.use_case.*;
import favourite.use_case.FavouritePostReaderInterface;
import filter_post.drivers.FilterPostDataAccess;
import filter_post.interface_adapters.FilterPostController;
import filter_post.interface_adapters.FilterPostPresenter;
import filter_post.interface_adapters.FilterPostViewModel;
import filter_post.ui.FilterPostBarView;
import filter_post.use_case.FilterPostDsGateway;
import filter_post.use_case.FilterPostInputBoundary;
import filter_post.use_case.FilterPostInteractor;
import filter_post.use_case.FilterPostOutputBoundary;
import log_in.drivers.LogInDatabaseAccess;
import log_in.interface_adapters.LogInController;
import log_in.interface_adapters.LogInPresenter;
import log_in.interface_adapters.LogInViewModel;
import log_in.ui.LogInView;
import log_in.use_case.*;
import log_out.interface_adapters.LogOutController;
import log_out.interface_adapters.LogOutPresenter;
import log_out.interface_adapters.LogOutViewModel;
import log_out.ui.LogOutView;
import log_out.use_case.LogOutInputBoundary;
import log_out.use_case.LogOutInteractor;
import log_out.use_case.LogOutOutputBoundary;
import make_comment.driver.MakeCommentDatabaseAccess;
import make_comment.interface_adapter.MakeCommentController;
import make_comment.interface_adapter.MakeCommentPresenter;
import make_comment.interface_adapter.MakeCommentViewModel;
import make_comment.ui.MakeCommentView;
import make_comment.use_case.MakeCommentFactory;
import make_comment.use_case.MakeCommentDsGateway;
import make_comment.use_case.MakeCommentInteractor;
import make_post.drivers.MakePostDatabaseAccess;
import make_post.interface_adapters.MakePostController;
import make_post.interface_adapters.MakePostPresenter;
import make_post.interface_adapters.MakePostViewModel;
import make_post.ui.MakePostView;
import make_post.use_case.MakePostDsGateway;
import make_post.use_case.MakePostInputBoundary;
import make_post.use_case.MakePostInteractor;
import make_post.use_case.MakePostOutputBoundary;
import sign_up.drivers.SignUpDatabaseAccess;
import sign_up.interface_adapters.SignUpController;
import sign_up.interface_adapters.SignUpPresenter;
import sign_up.interface_adapters.SignUpViewModel;
import sign_up.ui.MasterLandingView;
import sign_up.ui.SignUpView;
import sign_up.use_case.*;
import view_comment.drivers.ViewCommentDatabaseAccess;
import view_comment.interface_adapters.ViewCommentController;
import view_comment.interface_adapters.ViewCommentPresenter;
import view_comment.interface_adapters.ViewCommentViewModel;
import view_comment.ui.ViewCommentView;
import view_comment.use_case.ViewCommentDsGateway;
import view_comment.use_case.ViewCommentInputBoundary;
import view_comment.use_case.ViewCommentInteractor;
import view_comment.use_case.ViewCommentOutputBoundary;
import view_post.drivers.ViewPostDatabaseAccess;
import view_post.interface_adapters.ViewPostController;
import view_post.interface_adapters.ViewPostPresenter;
import view_post.interface_adapters.ViewPostViewModel;
import view_post.ui.*;
import view_post.use_case.ViewPostDsGateway;
import view_post.use_case.ViewPostInteractor;

/*
* The main class for the file, containing the main method which runs the program
* */
public class UofTeams {
    static final String[] tags = new String[]{"Sports", "Clubs", "Jobs", "Startups", "Tech", "School", "Projects"};
    static final String[] tagsWithFavourites = new String[]{"Favourites", "Sports", "Clubs", "Jobs", "Startups", "Tech", "School", "Projects"};
    static String postsFilePath = "src/main/java/database/posts.csv";
    static String usersFilePath = "src/main/java/database/users.csv";
    static String generalPath = "src/main/java/database/";

    public static void main(String[] args) {

        // initialize stuff for delete_post
        DeletePostViewModel deletePostViewModel = new DeletePostViewModel();
        DeletePostOutputBoundary deletePostPresenter = new DeletePostPresenter(deletePostViewModel);
        DeletePostReaderInterface deletePostFactory = new DeletePostFactory();
        DeletePostDsGateway deletePostDataAccess = new DeletePostDataAccess(generalPath, deletePostFactory);
        DeletePostInputBoundary deletePostInteractor = new DeletePostInteractor(deletePostPresenter, deletePostDataAccess);
        DeletePostController deletePostController = new DeletePostController(deletePostInteractor);
        DeleteView deleteView = new DeleteView(deletePostController);

        // initialize stuff for filter_post
        FilterPostViewModel filterPostViewModel = new FilterPostViewModel();
        FilterPostOutputBoundary filterPostPresenter = new FilterPostPresenter(filterPostViewModel);
        FilterPostDsGateway filterPostDataAccess = new FilterPostDataAccess(postsFilePath);
        FilterPostInputBoundary filterPostInteractor = new FilterPostInteractor(filterPostDataAccess, filterPostPresenter);
        FilterPostController filterPostController = new FilterPostController(filterPostInteractor);
        // Requires array of preset tags
        FilterPostBarView filterPostBarView = new FilterPostBarView(tagsWithFavourites, filterPostController);

        // initialize stuff for make_post
        MakePostViewModel makePostViewModel = new MakePostViewModel();
        MakePostOutputBoundary makePostPresenter = new MakePostPresenter(makePostViewModel);
        MakePostDsGateway makePostDatabaseAccess = new MakePostDatabaseAccess(generalPath);
        MakePostInputBoundary makePostInteractor = new MakePostInteractor(makePostDatabaseAccess, makePostPresenter);
        MakePostController makePostController = new MakePostController(makePostInteractor);
        MakePostView makePostView = new MakePostView(tags, makePostController);

        // initialize stuff for sign_up
        SignUpUserFactory signUpUserFactory = new SignUpUserFactory();
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        SignUpOutputBoundary signUpPresenter = new SignUpPresenter(signUpViewModel);
        SignUpDsGateway signUpDatabaseAccess = new SignUpDatabaseAccess(generalPath);
        SignUpInputBoundary signUpInteractor = new SignUpInteractor(signUpDatabaseAccess, signUpPresenter, signUpUserFactory);
        SignUpController signUpController = new SignUpController(signUpInteractor);
        SignUpView signUpView = new SignUpView(signUpController);

        // initialize stuff for make_comment
        MakeCommentFactory commentFactory = new MakeCommentFactory();
        MakeCommentViewModel makeCommentViewModel = new MakeCommentViewModel();
        MakeCommentPresenter makeCommentPresenter = new MakeCommentPresenter(makeCommentViewModel);
        MakeCommentDsGateway makeCommentDatabaseAccess = new MakeCommentDatabaseAccess(generalPath);
        MakeCommentInteractor makeCommentInteractor = new MakeCommentInteractor(makeCommentDatabaseAccess, makeCommentPresenter, commentFactory);
        MakeCommentController makeCommentController = new MakeCommentController(makeCommentInteractor);
        MakeCommentView makeCommentView = new MakeCommentView(makeCommentController);


        // initialize stuff for log in
        LogInUserFactory logInUserFactory = new LogInUserFactory();
        LogInViewModel logInViewModel = new LogInViewModel();
        LogInOutputBoundary logInPresenter = new LogInPresenter(logInViewModel);
        LogInDsGateway logInDatabaseAccess = new LogInDatabaseAccess(usersFilePath);
        LogInInputBoundary logInInteractor = new LogInInteractor(logInDatabaseAccess, logInPresenter, logInUserFactory);
        LogInController logInController = new LogInController(logInInteractor);
        LogInView logInView = new LogInView(logInController);

        // initialize stuff for log out
        LogOutViewModel logOutViewModel = new LogOutViewModel();
        LogOutOutputBoundary logOutPresenter = new LogOutPresenter(logOutViewModel);
        LogOutInputBoundary logOutInteractor = new LogOutInteractor(logOutPresenter);
        LogOutController logOutController = new LogOutController(logOutInteractor);
        LogOutView logOutView = new LogOutView(logOutController);

        // initialize stuff for favourite_post

        FavouriteUserReaderInterface userFactory = new FavouriteUserFactory();
        FavouritePostReaderInterface postFactory = new FavouritePostFactory();
        FavouriteViewModel favouriteViewModel = new FavouriteViewModel();
        FavouritePresenter favouritePresenter = new FavouritePresenter(favouriteViewModel);
        FavouriteDatabaseAccess favouriteDatabaseAccess = new FavouriteDatabaseAccess(postFactory, userFactory, generalPath);
        FavouriteInteractor favouriteInteractor = new FavouriteInteractor(favouriteDatabaseAccess, favouritePresenter);
        FavouriteController favouriteController = new FavouriteController(favouriteInteractor);
        FavouriteView favouriteView = new FavouriteView(favouriteController);

        // initialize stuff for view_comment
        ViewCommentViewModel viewCommentViewModel = new ViewCommentViewModel();
        ViewCommentDsGateway viewCommentDatabaseAccess = new ViewCommentDatabaseAccess(generalPath);
        ViewCommentOutputBoundary viewCommentPresenter = new ViewCommentPresenter(viewCommentViewModel);
        ViewCommentInputBoundary viewCommentInteractor = new ViewCommentInteractor(viewCommentDatabaseAccess, viewCommentPresenter);
        ViewCommentController viewCommentController = new ViewCommentController(viewCommentInteractor);
        ViewCommentView viewCommentView = new ViewCommentView(viewCommentController);

        // initialize stuff for view_post
        ViewPostViewModel viewPostViewModel = new ViewPostViewModel();
        ViewPostPresenter viewPostPresenter = new ViewPostPresenter(viewPostViewModel);
        ViewPostDsGateway viewPostGateway = new ViewPostDatabaseAccess(generalPath);
        ViewPostInteractor viewPostInteractor = new ViewPostInteractor(viewPostGateway, viewPostPresenter);
        ViewPostController viewPostController = new ViewPostController(viewPostInteractor);
        PostListView postListView = new PostListView(viewPostController, filterPostBarView);
        HeaderView headerView = new HeaderView(generalPath, makePostView, logOutView);
        ViewPostView viewPostView = new ViewPostView(favouriteView, makeCommentView, deleteView, viewCommentView, postListView);

        // initialize main view
        MasterLandingView masterLandingView = new MasterLandingView(signUpView, logInView);
        MainPostView mainPostView = new MainPostView(viewPostView, postListView, headerView);
        MainFrame mainFrame = new MainFrame(masterLandingView, mainPostView);

        // Setting up observers
        filterPostViewModel.addObserver(postListView);

        makePostViewModel.addObserver(makePostView);

        signUpViewModel.addObserver(signUpView);
        signUpViewModel.addObserver(mainFrame);
        signUpView.addObserver(masterLandingView);

        logInViewModel.addObserver(logInView);
        logInViewModel.addObserver(mainFrame);
        logInView.addObserver(masterLandingView);

        logOutViewModel.addObserver(mainFrame);

        viewPostViewModel.addObserver(viewPostView);

        makeCommentViewModel.addObserver(makeCommentView);

        viewCommentViewModel.addObserver(viewCommentView);

        favouriteViewModel.addObserver(favouriteView);

        deletePostViewModel.addObserver(deleteView);
        deletePostViewModel.addObserver(viewPostView);
        deletePostViewModel.addObserver(postListView);

        // Run it :)
        mainFrame.setUp();
    }
}
