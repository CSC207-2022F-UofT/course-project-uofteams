package deadline_timer.interface_adapters;

import deadline_timer.use_case.TimerOutputBoundary;
import deadline_timer.use_case.TimerResponseModel;

public class TimerPresenter implements TimerOutputBoundary {
    @Override
    public void present(TimerResponseModel responseModel) {
        // Here we will call the mainViewmodel, and tell it to refresh itself, using the observer design pattern.
    }
}
