package ch.fhnw.webec.gradr

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class GradeServiceSpec extends Specification {

    GradeService gradeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Grade(...).save(flush: true, failOnError: true)
        //new Grade(...).save(flush: true, failOnError: true)
        //Grade grade = new Grade(...).save(flush: true, failOnError: true)
        //new Grade(...).save(flush: true, failOnError: true)
        //new Grade(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //grade.id
    }

    void "test get"() {
        setupData()

        expect:
        gradeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Grade> gradeList = gradeService.list(max: 2, offset: 2)

        then:
        gradeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        gradeService.count() == 5
    }

    void "test delete"() {
        Long gradeId = setupData()

        expect:
        gradeService.count() == 5

        when:
        gradeService.delete(gradeId)
        sessionFactory.currentSession.flush()

        then:
        gradeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Grade grade = new Grade()
        gradeService.save(grade)

        then:
        grade.id != null
    }
}
