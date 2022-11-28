package make_post.use_case;

public interface MakePostInputBoundary {
    void makePost(MakePostRequestModel mprm);
    int getNumPostsCreated();
    int getCurrentUser();
    MakePostOutputBoundary getPresenter();
}
