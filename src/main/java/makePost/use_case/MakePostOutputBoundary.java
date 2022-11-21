package makePost.use_case;

import makePost.use_case.MakePostResponseModel;

public interface MakePostOutputBoundary {
    void updateViewModel(MakePostResponseModel responseModel);
}
