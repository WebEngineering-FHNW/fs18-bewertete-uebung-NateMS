package ch.fhnw.webec.gradr

class Course {

    String name
    static belongsTo = [ semester: Semester ]
    static hasMany = [grades:Grade]

    static constraints = {
    }

    User user() {
        return this.semester.user
    }

    double average() {
        if (grades.size() == 0) {
            return 0.0
        }

        GradeCalcuateService gcs = new GradeCalcuateService()

        List<Grade> cgrades = []
        cgrades.addAll(grades)

        return gcs.getAverage(cgrades)
    }
}
