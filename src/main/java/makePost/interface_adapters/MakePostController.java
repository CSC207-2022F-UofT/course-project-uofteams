package makePost.interface_adapters;

import makePost.use_case.MakePostInputBoundary;
import makePost.use_case.MakePostRequestModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MakePostController {
    private final MakePostInputBoundary interactor;

    /**
     * This is the controller for the use case. It passes the informatino for making a post to the interactor.
     * @param interactor The interactor for the use case.
     */
    public MakePostController(MakePostInputBoundary interactor){
        this.interactor = interactor;
    }

    public void passToInteractor(Map<String, Object> postBody){
        //raise an error if deadline is not in the right format.
        List<String> deadlineList = new ArrayList<>(Arrays.asList(((String) postBody.get("deadline")).split("-")));
        try{
        if(Integer.parseInt(deadlineList.get(0)) < LocalDate.now().getYear() || Integer.parseInt(deadlineList.get(1)) > 12
                || Integer.parseInt(deadlineList.get(1)) < 1 || Integer.parseInt(deadlineList.get(2)) > 31 ||
                Integer.parseInt(deadlineList.get(2)) < 1){
            //propagate error message
        }
        }
        catch(NumberFormatException n){
            //propagate error message

        }


        int numPostsCreated = interactor.getNumPostsCreated();
        Map<String, String> poster = interactor.getCurrentUser();

        //converting deadline to LocalDate.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.getDefault());
        LocalDate deadline = LocalDate.parse((String) postBody.get("deadline"));

        postBody.put("deadline", deadline);
        postBody.put("numPostsCreated", numPostsCreated);
        postBody.put("poster", poster);
        MakePostRequestModel makePostRequestModel = new MakePostRequestModel(postBody);
        interactor.makePost(makePostRequestModel);
    }
}
