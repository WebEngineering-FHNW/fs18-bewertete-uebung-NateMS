package ch.fhnw.webec.gradr

import grails.gorm.transactions.Transactional

@Transactional
class GradeCalcuateService {

    def getAverage(List<Grade> grades) {
        float sum = 0
        float count = 0

        boolean hasFinal = false
        float finalGrade = 1


        for (Grade grade : grades) {
            if (!grade.isFinal) {
                sum += grade.grade * grade.weight
                count += 1 * grade.weight
                continue
            }

            if (hasFinal) {
                throw new IllegalStateException("final grade has already been set")
            }

            hasFinal = true
            finalGrade = grade.grade
        }

        double average = getAverageToTenth(sum, count)

        if (hasFinal) {
            return getAverageToQuarter(average + finalGrade, 2)
        }

        return average
    }

    private static def getAverageToTenth(sum, count) {
        return Double.parseDouble(String.format("%.1f", sum / count))
    }

    private static def getAverageToQuarter(sum, count) {
        return Math.round((float)(sum / count) * 4) / 4f
    }

    def isPassing(Grade grade) {
        return isPassing(grade.grade)
    }

    def isPassing(double grade) {
        return grade >= 3.75
    }
}
