package deadline_timer.interface_adapters;

import deadline_timer.use_case.TimerInputBoundary;
import deadline_timer.use_case.TimerRequestModel;

import java.time.LocalDate;

public class TimerController {
    private final TimerInputBoundary inputBoundary;

    public TimerController(TimerInputBoundary timerInputBoundary) {
        this.inputBoundary = timerInputBoundary;
    }

    public void timer() {
        LocalDate date = LocalDate.now();
        TimerRequestModel requestModel = new TimerRequestModel(date);

        this.inputBoundary.timer(requestModel);

    }
}
