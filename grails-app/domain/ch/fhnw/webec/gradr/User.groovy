package ch.fhnw.webec.gradr

class User {

    static hasMany = [semesters:Semester]

    String email
    String password
    String firstname
    String lastname

    static constraints = {
        email(unique:true)
        password(password:true)
    }

    String name() {
        return firstname + " " + lastname
    }
}
