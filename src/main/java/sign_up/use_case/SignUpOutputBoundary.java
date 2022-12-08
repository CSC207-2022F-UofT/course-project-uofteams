package sign_up.use_case;

/**
 * Public Interface for the output of the sign up use case interactor
 */
public interface SignUpOutputBoundary {
    /**
     * Properly format the output from the interactor, and update the SignUpViewModel with a
     * success or error view
     * @param responseModel The SignUpResponseModel carrying the results of signing the user up
     */
    void updateViewModel(SignUpResponseModel responseModel);
}
