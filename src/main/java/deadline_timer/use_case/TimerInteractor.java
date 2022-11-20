package deadline_timer.use_case;

import entities.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimerInteractor implements TimerInputBoundary{
    private final TimerDataAccessInterface dataAccess;

    private final TimerOutputBoundary outputBoundary;

    private final List<Post> expiredPosts;

    public TimerInteractor(TimerDataAccessInterface dataAccess, TimerOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.expiredPosts = new ArrayList<>();
    }


    @Override
    public void timer(TimerRequestModel requestModel) {
        ArrayList<Post> allPosts = (ArrayList<Post>) dataAccess.getPosts();

        checkDates(allPosts, requestModel.date);

        deletePosts(this.expiredPosts);

        boolean refresh = !this.expiredPosts.isEmpty();

        TimerResponseModel responseModel = new TimerResponseModel(refresh);
        this.outputBoundary.present(responseModel);
    }

    private void checkDates(List<Post> posts, LocalDate date) {
        for (Post post: posts) {
            if (post.getDeadline().isAfter(date) || post.getDeadline().isEqual(date)) {
                this.expiredPosts.add(post);
            }
        }
    }

    private void deletePosts(List<Post> posts) {
        for (Post post: posts) {
            this.dataAccess.delete(post);
        }
    }
}
