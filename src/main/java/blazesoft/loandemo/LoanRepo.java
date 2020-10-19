package blazesoft.loandemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
// Temp repo
public class LoanRepo {
    private final Map<UUID, Loan> loanMap = new HashMap<>();

    public void save(Loan request) {
        loanMap.put(request.getId(), request);
    }

    public Optional<Loan> getById(UUID uuid) {
        return Optional.ofNullable(loanMap.get(uuid));
    }
}
