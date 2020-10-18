package blazesoft.loandemo

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.LocalDateTime

@Unroll
class LoanValidatorTest extends Specification {
    @Subject
    LoanValidator loanValidator

    LoanConfig loanConfig

    static final def morning = LocalDateTime.parse("2020-01-01T08:00:00")
    static final def night = LocalDateTime.parse("2020-01-01T03:00:00")

    void setup() {
        loanConfig = new LoanConfig(5000, 50000, 12, 36, 6)

        loanValidator = new LoanValidator(loanConfig)
    }

    def "should pass validation on #note"() {
        given:
        def loanRequest = new LoanRequest(givenAmount, givenPeriod, givenCreated)

        when:
        loanValidator.validate(loanRequest)

        then:
        noExceptionThrown()

        where:
        note                        | givenAmount | givenPeriod | givenCreated
        "average amount and period" | 10000       | 24          | morning
        "minimum amount"            | 5000        | 24          | morning
        "maximum amount"            | 50000       | 24          | morning
        "minimum period"            | 10000       | 12          | morning
        "maximum period"            | 10000       | 36          | morning
    }

    def "should throw LoanInvalidException on #note"() {
        given:
        def loanRequest = new LoanRequest(givenAmount, givenPeriod, givenCreated)

        when:
        loanValidator.validate(loanRequest)

        then:
        def exception = thrown(LoanInvalidException)
        exception.message == expectedExceptionMessage

        where:
        note                                           | givenAmount | givenPeriod | givenCreated | expectedExceptionMessage
        "invalid amount when amount 0"                 | 0           | 24          | morning      | "Invalid amount"
        "invalid amount when amount too high"          | 100000      | 24          | morning      | "Invalid amount"
        "invalid period when period 0"                 | 10000       | 0           | morning      | "Invalid period"
        "invalid period when period too high"          | 10000       | 48          | morning      | "Invalid period"
        "invalid when amount max and created at night" | 50000       | 24          | night        | "Invalid amount"
    }
}
