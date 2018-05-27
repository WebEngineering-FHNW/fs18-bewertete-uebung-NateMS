package ch.fhnw.webec.gradr

import java.util.concurrent.ThreadLocalRandom;

class BootStrap {

    def init = { servletContext ->
        def user = new User(email: 'test@example.com',  password: 'abc123', firstname: 'Nadim', lastname: 'Salloum').save()
        def semester4 = new Semester(description: 'Semester 4', user: user).save()
        def semester3 = new Semester(description: 'Semester 3', user: user).save()

        def ml = new Course(name: 'Machine Learning', semester: semester4).save()
        def conpr = new Course(name: 'Concurrent Programming', semester: semester4).save()
        def algd2 = new Course(name: 'Algorithmen und Datenstrukturen 2', semester: semester4).save()
        def webec = new Course(name: 'Web Engineering', semester: semester4).save()

        def cpp = new Course(name: 'C++', semester: semester3).save()
        def eti = new Course(name: 'Einführung in die theoretische Informatik', semester: semester3).save()
        def depa = new Course(name: 'Design Patterns', semester: semester3).save()


        /* second user */
        def u2 = new User(email: 'a@b.c',  password: 'pw', firstname: 'John', lastname: 'Doe').save()
        def u2semester4 = new Semester(description: 'Semester 4-2', user: u2).save()
        def u2ml = new Course(name: 'Machine Learning 2', semester: u2semester4).save()



        /* creating random grades for each course */
        List<Course> courses = [ml, conpr, algd2, webec, cpp, eti, depa, u2ml]
        for (course in courses) {
            int numOfGrades = ThreadLocalRandom.current().nextInt(2, 5); // 2 - 4 grades per subject
            for (int i = 0; i < numOfGrades; i++) {
                int grade = ThreadLocalRandom.current().nextInt(20, 60 + 1); // grade between 2 and 6

                new Grade(weight: 1, grade: (double)grade / 10, course: course).save()
            }
        }

    }
    def destroy = {
    }
}
