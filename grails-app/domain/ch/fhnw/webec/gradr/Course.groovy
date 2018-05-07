package ch.fhnw.webec.gradr

class Course {

    String name
    static belongsTo = [ semester: Semester ]

  //  List<Grade> grades = new ArrayList<>()

    static constraints = {
    }
}
