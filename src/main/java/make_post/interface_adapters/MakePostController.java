package make_post.interface_adapters;

import make_post.use_case.MakePostInputBoundary;
import make_post.use_case.MakePostRequestModel;
import make_post.use_case.MakePostResponseModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MakePostController {
    private final MakePostInputBoundary interactor;

    /**
     * This is the controller for the use case. It passes the information for making a post to the interactor.
     * @param interactor The interactor for the use case.
     */
    public MakePostController(MakePostInputBoundary interactor){
        this.interactor = interactor;
    }

    public void passToMakePostInteractor(Map<String, Object> postBody){
        int numPostsCreated = interactor.getNumPostsCreated();
        int currentUserID = interactor.getCurrentUser();

        try{
            //converting deadline to LocalDate.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter.withLocale(Locale.getDefault());
            LocalDate deadline = LocalDate.parse((String) postBody.get("deadline"));

            postBody.put("deadline", deadline);
            postBody.put("numPostsCreated", numPostsCreated);
            postBody.put("poster", currentUserID);
            MakePostRequestModel makePostRequestModel = new MakePostRequestModel(postBody);
            interactor.makePost(makePostRequestModel);
        }
        catch(NumberFormatException | IndexOutOfBoundsException | DateTimeParseException n){
            MakePostResponseModel responseModel = new MakePostResponseModel(false, "Date is not in the correct format.");
            interactor.getPresenter().updateViewModel(responseModel);
        }
    }
}