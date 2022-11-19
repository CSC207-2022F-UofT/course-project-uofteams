package makePost.interface_adapters;

import makePost.use_case.MakePostInputBoundary;
import makePost.use_case.MakePostRequestModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MakePostController {
    private final MakePostInputBoundary interactor;
    public MakePostController(MakePostInputBoundary interactor){
        this.interactor = interactor;
    }

    public void passToInteractor(Map<String, Object> postBody){
        //raise an error if deadline is not in the right format.

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
