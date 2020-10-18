package blazesoft.loandemo;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanValidator {
    private final LoanConfig config;

    public void validate(LoanRequest request) {
        if (request.getAmount() < config.getAmountMin()
              || request.getAmount() > config.getAmountMax()
              || (isNight(request.getCreated()) && request.getAmount().equals(config.getAmountMax()))
        ) {
            throw new LoanInvalidException("Invalid amount");
        }
        if (request.getPeriod() < config.getPeriodMin()
            || request.getPeriod() > config.getPeriodMax()) {
            throw new LoanInvalidException("Invalid period");
        }
    }

    private boolean isNight(LocalDateTime dateTime) {
        var startTime = dateTime.toLocalDate().atTime(0, 0);
        var endTime = dateTime.toLocalDate().atTime(6, 0);
        return (dateTime.isAfter(startTime) || dateTime.equals(startTime))
              && (dateTime.isBefore(endTime) || dateTime.equals(endTime));
    }
}
