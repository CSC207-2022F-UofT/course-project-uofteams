package make_comment.use_case;

public class MakeCommentRequestModel {
    //MakeCommentRequestModel is a class that stores input data for make comment use case

    // id of user making comment
    private final int userId;

    // The text body of the comment
    private final String body;

    // id of this comment
    private final int id;

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
    public MakeCommentRequestModel(int userId, String body, int id, int postId) {
        this.userId = userId;
        this.body = body;
        this.id = id;
        this.postId = postId;
    }
    public int getUserId(){
        return this.userId;
    }

    public String getCommentBody(){
        return this.body;
    }

    public int getSelfId(){
        return this.id;
    }

    public int getPostId(){
        return this.postId;
    }
}