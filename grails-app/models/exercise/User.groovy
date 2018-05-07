package exercise

class User {

    final int userId

    String email
    String password

    String firstname
    String lastname

    List<Semester> semesters = new ArrayList<>()

    static constraints = {

    }

    String name() {
        return firstName + " " + lastName
    }
}

