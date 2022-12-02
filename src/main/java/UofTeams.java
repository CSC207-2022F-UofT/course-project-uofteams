import delete_post.drivers.DeletePostDataAccess;
import delete_post.interface_adapters.DeletePostController;
import delete_post.interface_adapters.DeletePostPresenter;
import delete_post.interface_adapters.DeletePostViewModel;
import delete_post.use_case.DeletePostDsGateway;
import delete_post.use_case.DeletePostInputBoundary;
import delete_post.use_case.DeletePostInteractor;
import delete_post.use_case.DeletePostOutputBoundary;
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
import log_in.use_case.LogInDsGateway;
import log_in.use_case.LogInInputBoundary;
import log_in.use_case.LogInInteractor;
import log_in.use_case.LogInOutputBoundary;
import log_out.interface_adapters.LogOutController;
import log_out.interface_adapters.LogOutPresenter;
import log_out.interface_adapters.LogOutViewModel;
import log_out.ui.LogOutView;
import log_out.use_case.LogOutInputBoundary;
import log_out.use_case.LogOutInteractor;
import log_out.use_case.LogOutOutputBoundary;
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
import sign_up.use_case.SignUpDsGateway;
import sign_up.use_case.SignUpInputBoundary;
import sign_up.use_case.SignUpInteractor;
import sign_up.use_case.SignUpOutputBoundary;
import view_post.ui.MainFrame;

/*
* The main class for the file, containing the main method which runs the program
* */
public class UofTeams {
    static String adminPath = new String("");
    static String numPostsCreatedFilePath = new String("");
    static String numUsersCreatedFilePath = new String("");
    static String numCommentsCreatedFilePath = new String("");
    static String postsFilePath = new String("");
    static String usersFilePath = new String("");
    static String commentsFilePath = new String("");
    static String generalPath = new String("src/main/java/Database/");

    public static void main(String[] args) {
        // initialize stuff for delete_post
        DeletePostViewModel deletePostViewModel = new DeletePostViewModel();
        DeletePostOutputBoundary deletePostPresenter = new DeletePostPresenter(deletePostViewModel);
        DeletePostDsGateway deletePostDataAccess = new DeletePostDataAccess(generalPath);
        DeletePostInputBoundary deletePostInteractor = new DeletePostInteractor((DeletePostPresenter) deletePostPresenter, deletePostDataAccess);
        DeletePostController deletePostController = new DeletePostController(deletePostInteractor);

        // initialize stuff for filter_post
        FilterPostViewModel filterPostViewModel = new FilterPostViewModel(new String[0], new int[0], new String[0]);
        FilterPostOutputBoundary filterPostPresenter = new FilterPostPresenter(filterPostViewModel);
        FilterPostDsGateway filterPostDataAccess = new FilterPostDataAccess(postsFilePath);
        FilterPostInputBoundary filterPostInteractor = new FilterPostInteractor(filterPostDataAccess, filterPostPresenter);
        FilterPostController filterPostController = new FilterPostController(filterPostInteractor);
        // Requires array of preset tags
        FilterPostBarView filterPostBarView = new FilterPostBarView(new String[0], filterPostController);

        // initialize stuff for make_post
        MakePostViewModel makePostViewModel = new MakePostViewModel();
        MakePostOutputBoundary makePostPresenter = new MakePostPresenter(makePostViewModel);
        MakePostDsGateway makePostDatabaseAccess = new MakePostDatabaseAccess(generalPath);
        MakePostInputBoundary makePostInteractor = new MakePostInteractor(makePostDatabaseAccess, makePostPresenter);
        MakePostController makePostController = new MakePostController(makePostInteractor);
        MakePostView makePostView = new MakePostView(new String[0], makePostController);

        // initialize stuff for sign_up
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        SignUpOutputBoundary signUpPresenter = new SignUpPresenter(signUpViewModel);
        SignUpDsGateway signUpDatabaseAccess = new SignUpDatabaseAccess(usersFilePath, adminPath, numUsersCreatedFilePath);
        SignUpInputBoundary signUpInteractor = new SignUpInteractor(signUpDatabaseAccess, signUpPresenter);
        SignUpController signUpController = new SignUpController(signUpInteractor);
        SignUpView signUpView = new SignUpView(signUpController);

        // initialize stuff for make_comment
        /*
        MakeCommentViewModel makeCommentViewModel = new MakeCommentViewModel();
        MakeCommentPresenter makeCommentPresenter = new MakeCommentPresenter(makeCommentViewModel);
        MakeCommentDatabaseAccess makeCommentDatabaseAccess = new MakeCommentDatabaseAccess(numCommentsCreatedFilePath, commentsFilePath);
        MakeCommentInteractor makeCommentInteractor = new MakeCommentInteractor(makeCommentDatabaseAccess, makeCommentPresenter);
        MakeCommentController makeCommentController = new MakeCommentController(makeCommentInteractor);
        MakeCommentView makeCommentView = new MakeCommentView(makeCommentController);
         */

        // initialize stuff for timer
        /*
        PostFactory postFactory = new PostFactory();
        TimerDataAccess timerDataAccess = new TimerDataAccess(postsFilePath, postFactory);
        TimerInteractor timerInteractor = new TimerInteractor(timerDataAccess, deletePostInteractor);
        TimerController timerController = new TimerController(timerInteractor);
         */

        // initialize stuff for log in
        LogInViewModel logInViewModel = new LogInViewModel();
        LogInOutputBoundary logInPresenter = new LogInPresenter(logInViewModel);
        LogInDsGateway logInDatabaseAccess = new LogInDatabaseAccess(usersFilePath);
        LogInInputBoundary logInInteractor = new LogInInteractor(logInDatabaseAccess, logInPresenter);
        LogInController logInController = new LogInController(logInInteractor);
        LogInView logInView = new LogInView(logInController);

        // initialize stuff for log out
        LogOutViewModel logOutViewModel = new LogOutViewModel();
        LogOutOutputBoundary logOutPresenter = new LogOutPresenter(logOutViewModel);
        LogOutInputBoundary logOutInteractor = new LogOutInteractor(logOutPresenter);
        LogOutController logOutController = new LogOutController(logOutInteractor);
        LogOutView logOutView = new LogOutView(logOutController);

        // initialize stuff for favourite_post
        /*
        UserFactory userFactory = new UserFactory();
        FavourtiteViewModel favourtiteViewModel = new FavouriteViewModel();
        FavouritePresenter favouritePresenter = new FavouritePresenter(favourtiteViewModel);
        DataAccess dataAccess = new DataAccess(postFactory, userFactory, generalPath);
        FavouriteInteractor favouriteInteractor = new FavouriteInteractor(dataAccess, favouritePresenter);
        FavouriteController favouriteController = new FavouriteController(favouriteInteractor);
         */

        // initialize stuff for view_post
        /*
        ViewPostViewModel viewPostViewModel = new ViewPostViewModel();
        ViewPostPresenter viewPostPresenter = new ViewPostPresenter(viewPostViewModel);
        ViewPostGateway viewPostGateway = new ViewPostGateway();
        ViewPostInteractor viewPostInteractor = new ViewPostInteractor(viewPostGateway, viewPostPresenter);
        ViewPostController viewPostController = new ViewPostController(viewPostInteractor);
        PostListView postListView = new PostListView(viewPostController);
        ViewPostView viewPostView = new ViewPostView(deletePostView, favouritePostView, makeCommentView);
        HeaderView headerView = new HeaderView(makePostController, LogOutController);
         */

        // initialize main view
        MasterLandingView masterLandingView = new MasterLandingView(signUpView, logInView);
        // MainPostView mainPostView = new MainPostView(viewPostView, postListView, headerView);
        MainFrame mainFrame = new MainFrame(masterLandingView /*mainPostView*/);

        // Setting up observers
        // filterPostViewModel.addObserver(postListView);
        makePostViewModel.addObserver(makePostView);

        signUpViewModel.addObserver(signUpView);
        signUpViewModel.addObserver(mainFrame);
        signUpView.addObserver(masterLandingView);

        logInViewModel.addObserver(logInView);
        logInViewModel.addObserver(mainFrame);
        logInView.addObserver(masterLandingView);

        logOutViewModel.addObserver(mainFrame);

        // Run it :)
        mainFrame.setUp();
    }
}
