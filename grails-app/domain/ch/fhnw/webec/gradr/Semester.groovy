package ch.fhnw.webec.gradr

class Semester {

  //  List<Course> courses = new ArrayList<>()
    String description

    static belongsTo = [ user: User ]

    static constraints = {
    }
}
