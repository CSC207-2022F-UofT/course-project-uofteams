package deadline_timer.use_case;

import entities.CurrentUser;
import entities.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TimerInteractor implements TimerInputBoundary{
    private final TimerDSGateway dataAccess;

    private final TimerOutputBoundary outputBoundary;

    private final List<Integer> expiredPosts;
    private final DeletePostInteractor deletePostInteractor;

    public TimerInteractor(TimerDSGateway dataAccess, TimerOutputBoundary outputBoundary,
                           DeletePostInteractor deleteInteractor) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.expiredPosts = new ArrayList<Integer>();
        this.deletePostInteractor = deleteInteractor;
    }

    @Override
    public void timer(TimerRequestModel requestModel) {
        HashMap<Integer, Post> postHashMap = (HashMap<Integer, Post>) dataAccess.getPosts();

        checkDates(postHashMap, requestModel.date);

        deletePosts(postHashMap);

        boolean refresh = !this.expiredPosts.isEmpty();

        TimerResponseModel responseModel = new TimerResponseModel(refresh);
        this.outputBoundary.present(responseModel);
    }

    /*
    * Check through the values of the given map to see which are past the deadline, and add them to
    * the expiredPosts
    *
    *
    * */
    private void checkDates(HashMap<Integer, Post> hashMap, LocalDate date) {
        Set<Integer> keys = hashMap.keySet();
        for (Integer key : keys) {
            if (hashMap.get(key).getDeadline().isAfter(date) || hashMap.get(key).getDeadline().isEqual(date)) {
                this.expiredPosts.add(key);
            }
        }
    }

    /*
    * Delete the posts in posts by calling the delete post use case
    * */
    private void deletePosts(HashMap<Integer, Post> hashMap) {
        for (Integer postId: this.expiredPosts) {
            DeletePostRequestModel requestModel = new DeletePostRequestModel(hashMap.get(postId), true);
            this.deletePostInteractor.delete(requestModel);
        }
    }
}
