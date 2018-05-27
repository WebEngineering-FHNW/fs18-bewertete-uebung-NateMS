package ch.fhnw.webec.gradr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SemesterController {

    SemesterService semesterService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Semester.findAllByUser(session.user), model:[semesterCount: Semester.count()], newsemester:new Semester(params)
    }

    def show(Long id) {
        Semester s = Semester.get(id)
        if (!s || s.user?.email != session.user?.email) {
            notFound()
            return
        }

        def courses = Course.where {
            semester == s
        }.list(sort: 'name', order: 'asc')

        respond s, model:[courses: courses]
    }

    def edit(Long id) {
        Semester s = Semester.get(id)
        if (!s || s.user?.email != session.user?.email) {
            notFound()
            return
        }

        def courses = Course.where {
            semester == s
        }.list(sort: 'name', order: 'asc')

        respond s, model:[courses: courses]
    }

    def save(Semester semester) {
        if (semester == null || !session.user) {
            notFound()
            return
        }

        semester.user = session.user

        try {
            semesterService.save(semester)
        } catch (ValidationException e) {
            respond semester.errors, view:'index'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = semester.description + ' has been created'
                flash.class = "success"
                redirect semester
            }
            '*' { respond index, [status: CREATED] }
        }
    }

    def update(Semester semester) {
        Semester s = Semester.findById(semester.id)
        if (!s || s.user?.email != session.user?.email) {
            notFound()
            return
        }

        s.description = semester.description

        try {
            semesterService.save(s)
        } catch (ValidationException e) {
            respond semester.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = "Semester has been renamed to '" + s.description + "'"
                flash.class = "success"
                redirect semester
            }
            '*'{ respond semester, [status: OK] }
        }
    }

    def delete(Long id) {
        Semester semester = semesterService.get(id)
        if (id == null || semester.user.email != session.user?.email) {
            notFound()
            return
        }

        semesterService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = "semester deleted"
                flash.class = "success"
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = "not found / not allowed"
                flash.class = "error"
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
