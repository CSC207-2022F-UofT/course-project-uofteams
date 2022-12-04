package make_comment.use_case;

public class MakeCommentRequestModel {
    //MakeCommentRequestModel is a class that stores input data for make comment use case

    // The text body of the comment
    private final String body;

    // id of the post this comment is associated to
    private final int postId;


    /*
     * Initializes a SignUpResponseModel
     *
     * @param email String representation of the inputted email
     * @param password String representation of the inputted password
     * @param checkAdmin boolean rep of whether to check adminPassword
     * @param adminPassword String rep of admin password to check
     */
    public MakeCommentRequestModel(String body, int postId) {
        this.body = body;
        this.postId = postId;
    }

    public String getCommentBody(){
        return this.body;
    }

    public int getPostId(){
        return this.postId;
    }
}