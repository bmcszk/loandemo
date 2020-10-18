package blazesoft.loandemo;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Value;
import org.springframework.lang.NonNull;

@Value
@Getter
public class LoanRequest {
    @NonNull
    Integer amount;
    @NonNull
    Integer period;
    @NonNull
    LocalDateTime created;
}
