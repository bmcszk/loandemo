package blazesoft.loandemo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class LoanExtensionException extends RuntimeException {
    public LoanExtensionException(String message) {
        super(message);
    }
}
