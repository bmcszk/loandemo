package blazesoft.loandemo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Loan {
    UUID id;
    int amount;
    int period;
    LocalDateTime created;
    boolean extended;
    int interestRate;
    LocalDate repaymentDate;
}
