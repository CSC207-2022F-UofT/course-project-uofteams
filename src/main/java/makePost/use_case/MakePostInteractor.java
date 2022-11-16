package makePost.use_case;

import entities.Post;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class MakePostInteractor {
    private MakePostRequestModel mprm;
    private LocalDate deadline;

    public MakePostInteractor(MakePostRequestModel mprm){
        this.mprm = mprm;
    }

    /**
     * Checks whether the deadline is at most six months from the creationDate.
     * @return true if the above condition is met.
     */
    public boolean checkDeadline(MakePostRequestModel mprm){
        LocalDate deadline = mprm.getDeadline();
        LocalDate creationDate = mprm.getCreationDate();
        if((deadline != null) && (DAYS.between(deadline, creationDate) <= 182.5)){
            return true;
        }
        return false;
    }

    public Post createPost(MakePostRequestModel mprm){
        return new Post(mprm.getPoster(), mprm.getTitle(), mprm.getMainDesc(), mprm.getTags(), mprm.getCollaborators(),
                mprm.getDeadline(), mprm.getCreationDate(), mprm.getNumPostsCreated());
    }



}
