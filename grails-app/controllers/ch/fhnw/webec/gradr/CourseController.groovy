package ch.fhnw.webec.gradr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseController {

    CourseService courseService
    GradeCalcuateService gs = new GradeCalcuateService()

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect action: 'index', controller: 'Semester'
    }

    def show(Long id) {
        Course c = Course.get(id)
        if (c.semester.user.email != session.user?.email) {
            notFound()
            return
        }

        def normalGrades = Grade.where {
            course == c
            isFinal == false
        }.list(sort: 'grade', order: 'asc')

        def finalGrades = Grade.where {
            course == c
            isFinal == true
        }.list(sort: 'grade', order: 'asc')

        def preAverage = false
        if (normalGrades && finalGrades) {
            preAverage = gs.getAverage(normalGrades)
        }

        respond c, model:[normalGrades: normalGrades, finalGrades: finalGrades, preAverage: preAverage]
    }

    def save(Course course) {
        Semester s = Semester.findById(params.semesterId)
        if (course == null || s?.user?.email != session.user?.email) {
            notFound()
            return
        }

        try {
            course.name = params.name
            course.semester = s
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = "Course '" + course.name + "' created"
                flash.class = "success"
                redirect action: 'show', controller: 'Semester', id : course.semesterId
            }
            '*' { respond course, [status: CREATED] }
        }
    }

    def edit(Long id) {
        Course c = courseService.get(id)
        if (id == null || c.semester.user.email != session.user?.email) {
            notFound()
            return
        }

        // bad, copy pasted from show()
        def normalGrades = Grade.where {
            course == c
            isFinal == false
        }.list(sort: 'grade', order: 'asc')

        def finalGrades = Grade.where {
            course == c
            isFinal == true
        }.list(sort: 'grade', order: 'asc')

        def preAverage = false
        if (normalGrades && finalGrades) {
            preAverage = gs.getAverage(normalGrades)
        }

        respond c, model:[normalGrades: normalGrades, finalGrades: finalGrades, preAverage: preAverage]
    }

    def update(Course course) {
        if (course == null || course.semester.user.email != session.user?.email) {
            notFound()
            return
        }

        try {
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = "Course has been renamed to '" + course.name + "'"
                flash.class = "success"
                redirect course
            }
            '*'{ respond course, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null || course.semester.user.email != session.user?.email) {
            notFound()
            return
        }

        courseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = "course deleted"
                flash.class = "success"
                redirect controller: "semester", action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = "not found / not allowed"
                flash.class = "error"
                redirect controller: "semester", action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
