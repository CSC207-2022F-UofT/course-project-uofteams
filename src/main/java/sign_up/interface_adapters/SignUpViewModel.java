package sign_up.interface_adapters;

import sign_up.use_case.*;

public class SignUpViewModel implements SignUpOutputBoundary {
    // The view which will be updated following the viewmodel
    private final View view;
    private final SignUpInputBoundary inputBoundary;

    public SignUpViewModel(SignUpDataAccessInterface dataAccess, View view) {
        inputBoundary = new SignUpInteractor(dataAccess, this);
        this.view = view;
    }

    public void signUp(String email, String password, String adminPassword) {
        SignUpRequestModel requestModel;
        if (adminPassword.isEmpty()) {
            requestModel = new SignUpRequestModel(email, password, false, "");
        } else {
            requestModel = new SignUpRequestModel(email, password, true, adminPassword);
        }
        inputBoundary.signUp(requestModel);
    }


    @Override
    public void present(SignUpResponseModel responseModel) {
        if (responseModel.creationSuccess) {
            view.update(true, "");
        } else {
            view.update(false, responseModel.message);
        }
    }

}
