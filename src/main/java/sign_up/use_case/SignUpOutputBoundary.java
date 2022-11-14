package sign_up.use_case;

/*
* Interface which contains result of attempt to create a user
*
* */
public interface SignUpOutputBoundary {
    void present(SignUpResponseModel responseModel);
}
