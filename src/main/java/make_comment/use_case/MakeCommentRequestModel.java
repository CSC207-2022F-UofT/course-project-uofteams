package make_comment.use_case;

public class MakeCommentRequestModel {
    //MakeCommentRequestModel is a class that stores input data for make comment use case

    // id of user making comment
    private int userId;

    // The text body of the comment
    private String body;

    // id of this comment
    private int id;


    /*
     * Initializes a SignUpResponseModel
     *
     * @param email String representation of the inputted email
     * @param password String representation of the inputted password
     * @param checkAdmin boolean rep of whether to check adminPassword
     * @param adminPassword String rep of admin password to check
     */
    public MakeCommentRequestModel(int userId, String body, int id) {
        this.userId = userId;
        this.body = body;
        this.id = id;
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
}