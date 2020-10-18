package blazesoft.loandemo;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoanController {
    private final LoanService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Loan createLoan(@NonNull @RequestBody LoanRequest request) {
        log.info("createLoan");
        return service.createLoan(request);
    }

    @PatchMapping("{loanId}")
    public Loan extendLoan(@PathVariable UUID loanId) {
        log.info("extendLoan");
        return service.extendLoan(loanId);
    }
    
}
