package view_comment.use_case;

import entities.CurrentUser;

public class ViewCommentInteractor implements ViewCommentInputBoundary{
    @Override
    public int getCurrentPostID() {
        return CurrentUser.getCurrentUser().getId();
    }
}
