package ch.fhnw.webec.gradr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class GradeController {

    GradeService gradeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond gradeService.list(params), model:[gradeCount: gradeService.count()]
    }

    // there are three possibilites
    // I want to limit the courses in the select field based on where we come from
    def create(long semId, long courId) {
        def courses = [];
        if (courId) {
            Course c = Course.findById(courId);
            if (c.semester.user.email == session.user?.email) {
                courses = [c]
            }
        } else if (semId) {
            Semester s = Semester.findById(semId)
            if (s.user.email == session.user?.email) {
                courses = Course.findAllBySemester(s)
            }
        }

        if (courses.size() == 0) {
            def semesters = Semester.findAllByUser(session.user)
            courses = []
            for (s in semesters) {
                courses += Course.findAllBySemester(s)
            }
        }

        respond new Grade([weight:1, grade:5.0]), model: [courses : courses]
    }

    def save(Grade grade) {
        if (grade == null) {
            notFound()
            return
        }

        if(!(session.user?.email == grade.course?.semester?.user?.email) ){
            flash.message = "Sorry, you can only edit your own entries."
            flash.class = "error"
            redirect(controller: "semester", action: "index")
        }

        try {
            grade.grade = ((float)grade.grade) / 10
            /* I'm not sure what is happening, but a 5.0 evaluates to a 50 in the end and a 5 works.
                I divided by temp so decimals work, but now of course those numbers without decimals are off
                by an order of magnitude
             */

            gradeService.save(grade)
        } catch (ValidationException e) {
            respond grade.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'The grade ' + grade.grade + ' has been added to ' + grade.course.name
                flash.class = "success"
                redirect(controller: "course", action: "show", id: grade.courseId)
            }
            '*' { respond grade, [status: CREATED] }
        }
    }

    def delete(Long id) {
        Grade g = Grade.findById(id)
        def course = g.course
        if (!g || session.user?.email != course.semester.user.email) {
            notFound()
            return
        }

        gradeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = 'The grade has been deleted from ' + course.name
                flash.class = "success"
                redirect controller: "course", action: "show", id: course.id, method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = "not found / not allowed"
                flash.class = "error"
                redirect controller: "course", action: "show", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
