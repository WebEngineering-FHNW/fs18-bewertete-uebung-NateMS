package ch.fhnw.webec.gradr

class Semester {

    static hasMany = [courses:Course]
    String description

    static belongsTo = [ user: User ]

    static constraints = {
    }
}
