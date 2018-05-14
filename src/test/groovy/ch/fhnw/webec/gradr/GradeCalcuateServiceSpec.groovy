package ch.fhnw.webec.gradr

import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification
import grails.gorm.transactions.*

class GradeCalcuateServiceSpec extends HibernateSpec implements ServiceUnitTest<GradeCalcuateService>{

    GradeCalcuateService gcs
    Grade g1, g2, g3, g4, g5, g6, g35, g475

    List<Grade> grades

    def setup() {
        gcs = new GradeCalcuateService()
        float weight = 1
        g1 = new Grade(grade: 1, weight: weight)
        g2 = new Grade(grade: 2, weight: weight)
        g3 = new Grade(grade: 3, weight: weight)
        g4 = new Grade(grade: 4, weight: weight)
        g5 = new Grade(grade: 5, weight: weight)
        g6 = new Grade(grade: 6, weight: weight)

        g35 = new Grade(grade: 3.5, weight: weight)
        g475 = new Grade(grade: 4.75, weight: weight)
    }

    def cleanup() {
    }

    void "test getting the average"() {
        when:"the average is a decimal number"
        grades = [g2, g5]
        then:"it should be calculated correctly"
            gcs.getAverage(grades) == 3.5

        when:"the average is the same number out of multiple inputs"
        grades = [g3, g3, g3, g3]
        then:"it should be calculated correctly"
            gcs.getAverage(grades) == 3

        when:"the average out of inputs with decimal numbers"
        grades = [g35, g475, g5]
        then:"it should be calculated correctly"
            gcs.getAverage(grades) == 4.4 /* 4.416666667 */

        when:"a grade is weigthed"
        Grade g4Weigthed = new Grade(grade:4, weight: 2)
        grades = [g3, g4Weigthed]
        then:"it should be calculated correctly"
        gcs.getAverage(grades) == 3.7

        when:"there is a final grade"
        Grade gFinal = new Grade(grade:2, isFinal:true)
        grades = [g4, g5, g475, gFinal]
        then:"it should count as 50% of the average"
        gcs.getAverage(grades) == 3.25 /* 3.3 rounded down to 3.25 */
    }

    void "test passing grade"() {
        when:"the grade is insufficient"
        grades = [g2, g5]
        then:"it should fail"
        !gcs.isPassing(gcs.getAverage(grades))

        when:"the grade is insufficient"
        then:"it should pass"
            gcs.isPassing(g475)

        when:"the grade is exactly 3.75"
        grades = [g35, g4]
        then:"it should pass"
            gcs.isPassing(gcs.getAverage(grades))
    }
}
