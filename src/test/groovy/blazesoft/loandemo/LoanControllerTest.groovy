package blazesoft.loandemo

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class LoanControllerTest extends Specification {
    @Subject
    LoanController controller

    LoanService serviceMock = Mock()

    LoanRequest request

    Loan loan

    UUID uuid

    void setup() {
        controller = new LoanController(serviceMock)
        request = new LoanRequest(1, 1, LocalDateTime.now())
        uuid = UUID.randomUUID()
        loan = Loan.builder()
                .id(uuid)
                .build()
    }

    def "should create loan"() {
        when:
        def response = controller.createLoan(request)

        then:
        1 * serviceMock.createLoan(request) >> loan
        response == loan
    }

    def "should extend loan"() {
        when:
        def response = controller.extendLoan(uuid)

        then:
        1 * serviceMock.extendLoan(uuid) >> loan
        response ==  loan
    }
}
