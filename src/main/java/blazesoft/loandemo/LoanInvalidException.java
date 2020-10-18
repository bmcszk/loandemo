package blazesoft.loandemo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanInvalidException extends RuntimeException {
    public LoanInvalidException(String message) {
        super(message);
    }
}
