package sign_up.interface_adapters;

import sign_up.use_case.SignUpInputBoundary;
import sign_up.use_case.SignUpRequestModel;

public class SignUpController {
    private final SignUpInputBoundary interactor;

    /*
    * Initialize a SignUpController instance
    * @param interactor The Input Boundary for the use case
    * */
    public SignUpController(SignUpInputBoundary interactor) {
        this.interactor = interactor;
    }

    /*
    * Calls input boundary for the use case
    *
    * @ param inputData The data to be passed to the use case
    * */
    public void signUp(SignUpUserInputData inputData) {
        SignUpRequestModel requestModel;
        if (inputData.getAdminInput().isEmpty()) {
            requestModel = new SignUpRequestModel(inputData.getEmailInput(), inputData.getPasswordInput(),
                    false, "");
        } else {
            requestModel = new SignUpRequestModel(inputData.getEmailInput(), inputData.getPasswordInput(),
                    true, inputData.getAdminInput());
        }

        interactor.signUp(requestModel);
    }


}
