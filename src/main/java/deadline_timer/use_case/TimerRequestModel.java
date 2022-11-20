package deadline_timer.use_case;

import java.time.LocalDate;

public class TimerRequestModel {
    public LocalDate date;

    public TimerRequestModel(LocalDate date){
        this.date = date;
    }
}
