package ch.fhnw.webec.gradr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SemesterController {

    SemesterService semesterService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond semesterService.list(fetch: [userId: session.user.id]), model:[semesterCount: semesterService.count()], newsemester:new Semester(params)
    }

    def show(Long id) {
        Semester s = semesterService.get(id)
        if (s.user.email != session.user?.email) {
            notFound()
            return
        }

        def courses = Course.where {
            semester == s
        }.list(sort: 'name', order: 'asc')

        respond semesterService.get(id), model:[courses: courses]
    }

    def edit(Long id) {
        Semester semester = semesterService.get(id)
        if (id == null || semester.user.email != session.user?.email) {
            notFound()
            return
        }

        respond semester
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
                redirect semester
            }
            '*' { respond index, [status: CREATED] }
        }
    }

    def update(Semester semester) {

        if (semester == null || semester.user.email != session.user?.email) {
            notFound()
            return
        }

        try {
            semesterService.save(semester)
        } catch (ValidationException e) {
            respond semester.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'semester.label', default: 'Semester'), semester.id])
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
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'semester.label', default: 'Semester'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'semester.label', default: 'Semester'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
