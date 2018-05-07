package ch.fhnw.webec.gradr

class BootStrap {

    def init = { servletContext ->
        def user = new User(email: 'test@example.com',  password: 'abc123', firstname: 'John', lastname: 'Doe').save()
        def semester4 = new Semester(description: 'Semester 4', user: user).save()

        def ml = new Course(name: 'Machine Learning', semester: semester4).save()
        def conpr = new Course(name: 'Concurrent Programming', semester: semester4).save()
        def algd2 = new Course(name: 'Algorithmen und Datenstrukturen 2', semester: semester4).save()

    }
    def destroy = {
    }
}
