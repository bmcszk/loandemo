package blazesoft.loandemo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {

    private final LoanValidator validator;
    private final LoanRepo repo;
    private final LoanConfig config;

    private static final int INTEREST_RATE = 7;

    public Loan createLoan(LoanRequest request) {
        validator.validate(request);
        var loan = createNewLoanFromRequest(request);
        repo.save(loan);
        return loan;
    }

    public Loan extendLoan(UUID loanId) {
        final var loan = repo.getById(loanId);
        if (loan.isExtended()) {
            throw new LoanExtensionException("Already extended");
        }
        final var newPeriod = loan.getPeriod() + config.getExtension();
        final var newLoan = loan.toBuilder()
              .period(newPeriod)
              .repaymentDate(calculateRepaymentDate(loan.getCreated(), newPeriod))
              .extended(true)
              .build();
        repo.save(newLoan);
        return newLoan;
    }

    private Loan createNewLoanFromRequest(LoanRequest request) {
        return Loan.builder()
              .id(UUID.randomUUID())
              .amount(request.getAmount())
              .period(request.getPeriod())
              .created(request.getCreated())
              .extended(false)
              .interestRate(INTEREST_RATE)
              .repaymentDate(calculateRepaymentDate(request.getCreated(), request.getPeriod()))
              .build();
    }

    private LocalDate calculateRepaymentDate(LocalDateTime created, int period) {
        return created.toLocalDate().plusMonths(period);
    }
}
