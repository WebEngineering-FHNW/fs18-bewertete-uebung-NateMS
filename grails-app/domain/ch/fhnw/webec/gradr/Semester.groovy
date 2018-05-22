package ch.fhnw.webec.gradr

class Semester {

    static hasMany = [courses:Course]
    String description

    static belongsTo = [ user: User ]

    static constraints = {
    }

    def average() {
        GradeCalcuateService gcs = new GradeCalcuateService()
        List<Grade> grades = []

        for (Course course in courses) {
            double g = course.average()
            if (g != 0.0) {
                grades << new Grade(weight: 1, grade: g)
            }
        }

        if (grades.size() == 0) {
            return 0.0
        }

        return gcs.getAverage(grades);
    }
}
