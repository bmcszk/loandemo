package blazesoft.loandemo

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate
import java.time.LocalDateTime

class LoanServiceTest extends Specification {
    @Subject
    LoanService service

    LoanValidator validatorMock = Mock()
    LoanRepo repoMock = Mock()
    LoanConfig config

    UUID uuid
    LocalDateTime created = LocalDateTime.parse("2020-01-01T08:00:00")
    LoanRequest request

    void setup() {
        config = new LoanConfig(1, 3, 2, 4, 5)
        service = new LoanService(validatorMock, repoMock, config)
        request = new LoanRequest(2, 3, created)
        uuid = UUID.randomUUID()
    }

    def "should create loan"() {
        when:
        def loan = service.createLoan(request)

        then:
        1 * validatorMock.validate(request)
        1 * repoMock.save(_ as Loan)
        loan.amount == 2
        loan.period == 3
        loan.created == created
        loan.interestRate == 7
        !loan.extended
        loan.repaymentDate == LocalDate.parse("2020-04-01")
    }

    def "should extend loan"() {
        given:
        def existingLoan = Loan.builder()
                .amount(2)
                .period(3)
                .created(created)
                .repaymentDate(LocalDate.parse("2020-04-01"))
                .extended(false)
                .build()

        when:
        def loan = service.extendLoan(uuid)

        then:
        1 * repoMock.getById(uuid) >> existingLoan
        loan.amount == existingLoan.amount
        loan.period == 8
        loan.created == existingLoan.created
        loan.repaymentDate == LocalDate.parse("2020-09-01")
        loan.extended
    }

    def "should not extend loan if already extended"() {
        given:
        def existingLoan = Loan.builder()
                .amount(2)
                .period(3)
                .created(created)
                .repaymentDate(LocalDate.parse("2020-04-01"))
                .extended(true)
                .build()

        when:
        service.extendLoan(uuid)

        then:
        1 * repoMock.getById(uuid) >> existingLoan
        def exception = thrown(LoanExtensionException)
        exception.message == "Already extended"
    }
}
