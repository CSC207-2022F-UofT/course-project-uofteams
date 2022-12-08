package sign_up.interface_adapters;

import sign_up.use_case.SignUpInputBoundary;
import sign_up.use_case.SignUpRequestModel;

/**
 * The Controller for the sign up use case. Takes raw input from the user and passes it through
 * the input boundary in the most convenient form
 */
public class SignUpController {
    private final SignUpInputBoundary signUpInteractor;

    /**
    * Initialize a SignUpController instance
    * @param signUpInteractor The Input Boundary for the use case
    */
    public SignUpController(SignUpInputBoundary signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    /**
    * Formats the inputData and passes through the input boundary to the use case interactor
    * @param inputData The inputted data from the user
    */
    public void signUp(SignUpUserInputData inputData) {
        SignUpRequestModel requestModel;
        if (inputData.getAdminInput().isEmpty()) {
            requestModel = new SignUpRequestModel(inputData.getEmailInput(), inputData.getPasswordInput(),
                    false, "");
        } else {
            requestModel = new SignUpRequestModel(inputData.getEmailInput(), inputData.getPasswordInput(),
                    true, inputData.getAdminInput());
        }

        signUpInteractor.signUp(requestModel);
    }


}
