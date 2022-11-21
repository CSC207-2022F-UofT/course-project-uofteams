package makePost.use_case;

import entities.Post;
import makePost.interface_adapters.MakePostViewModel;

import java.util.List;
import java.util.Map;

public interface MakePostInputBoundary {
    void makePost(MakePostRequestModel mprm);
    int getNumPostsCreated();
    int getCurrentUser();
    MakePostOutputBoundary getPresenter();
}
