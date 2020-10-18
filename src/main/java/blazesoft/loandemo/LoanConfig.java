package blazesoft.loandemo;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "loan")
@Value
@Getter
public class LoanConfig {
    @NonNull
    Integer amountMin;
    @NonNull
    Integer amountMax;
    @NonNull
    Integer periodMin;
    @NonNull
    Integer periodMax;
    @NonNull
    Integer extension;

}
