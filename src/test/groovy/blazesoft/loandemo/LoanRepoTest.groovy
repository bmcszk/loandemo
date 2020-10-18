package blazesoft.loandemo

import spock.lang.Specification
import spock.lang.Subject

class LoanRepoTest extends Specification {
    @Subject
    LoanRepo loanRepo

    Loan loan

    UUID uuid

    void setup() {
        loanRepo = new LoanRepo()
        uuid = UUID.randomUUID()
        loan = Loan.builder()
                .id(uuid)
                .amount(1)
                .period(1)
                .build()
    }

    def "should save"() {
        when:
        loanRepo.save(loan)

        then:
        noExceptionThrown()
    }

    def "should get by id"() {
        given:
        loanRepo.save(loan)

        when:
        def result = loanRepo.getById(uuid)

        then:
        result == loan
    }
}
