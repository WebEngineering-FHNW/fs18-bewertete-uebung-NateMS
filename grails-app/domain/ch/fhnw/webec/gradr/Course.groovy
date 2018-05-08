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
}
