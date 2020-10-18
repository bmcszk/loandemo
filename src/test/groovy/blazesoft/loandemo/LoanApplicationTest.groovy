package blazesoft.loandemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest
class LoanApplicationTest extends Specification {
    @Autowired
    ApplicationContext applicationContext

    def 'should load context'() {
        expect:
        applicationContext != null
    }
}
