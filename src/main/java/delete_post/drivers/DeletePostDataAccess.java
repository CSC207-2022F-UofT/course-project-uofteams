package delete_post.drivers;

import delete_post.use_case.DeletePostDsGateway;

import java.util.List;

public class DeletePostDataAccess implements DeletePostDsGateway{

    private final String path;

    public DeletePostDataAccess(String path){
        this.path = path;
    }

    @Override
    public void removeFavourite(int postId, int userId){

    }
    public void removeTag(int postId, String tag){

    }
    public void deletePost(int postId){

    }
}
