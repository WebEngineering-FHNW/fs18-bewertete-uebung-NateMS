package ch.fhnw.webec.gradr

class Grade {

    static belongsTo = [ course:Course ]

    int weight
    float grade
    boolean isFinal

    static constraints = {
    }

    Semester semester() {
        return this.course.semester
    }

    User user() {
        return this.course.semester.user
    }
}
