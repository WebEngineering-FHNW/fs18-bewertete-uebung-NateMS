package ch.fhnw.webec.gradr

import grails.gorm.transactions.Transactional

@Transactional
class GradeCalcuateService {

    def getAverage(List<Grade> grades) {
        float sum = 0
        int count = grades.size()

        boolean hasFinal = false
        float finalGrade = 1


        for (Grade grade : grades) {
            if (!grade.isFinal) {
                sum += grade.grade * grade.weight
                continue
            }

            if (hasFinal) {
                throw new IllegalStateException("final grade has already been set")
            }

            hasFinal = true
            finalGrade = grade.grade
            count--;
        }

        float average = getAverage(sum, count)

        if (hasFinal) {
            return getAverage((float)average + finalGrade, 2)
        }

        return average
    }

    def getAverage(float sum, int count) {
        return (sum / count)
    }
}
