package delete_post.use_case;

import entities.Postable;

public class DeletePostDsRequestModel {

    private Postable post;

    public DeletePostDsRequestModel(Postable post){
        this.post = post;
    }

    public Postable getPost(){
        return this.post;
    }

}