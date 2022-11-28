package sign_up.use_case;

/*
* Interface which takes data and attempts to create a new user
*
* */
public interface SignUpInputBoundary {
    void signUp(SignUpRequestModel requestModel);
}
