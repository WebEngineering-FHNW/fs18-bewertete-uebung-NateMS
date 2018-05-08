package ch.fhnw.webec.gradr

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class GradeController {

    GradeService gradeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def auth() {
        if(!session.user) {
            redirect(controller:"home")
            return false
        }
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond gradeService.list(params), model:[gradeCount: gradeService.count()]
    }

    def show(Long id) {
        Grade grade = gradeService.get(id)

        if(!(session.user?.email == grade.user().email) ){
            flash.message = "Sorry, you can only edit your own entries."
            redirect(action:index)
        }

        respond grade
    }

    def create() {
        respond new Grade(params)
    }

    def save(Grade grade) {
        if (grade == null) {
            notFound()
            return
        }

        if(!(session.user?.email == grade.user().email) ){
            flash.message = "Sorry, you can only edit your own entries."
            redirect(action:index)
        }

        try {
            gradeService.save(grade)
        } catch (ValidationException e) {
            respond grade.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'grade.label', default: 'Grade'), grade.id])
                redirect grade
            }
            '*' { respond grade, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond gradeService.get(id)
    }

    def update(Grade grade) {
        if (grade == null) {
            notFound()
            return
        }

        if(!(session.user.email == grade.user().email) ){
            flash.message = "Sorry, you can only edit your own entries."
            redirect(action:index)
        }

        try {
            gradeService.save(grade)
        } catch (ValidationException e) {
            respond grade.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'grade.label', default: 'Grade'), grade.id])
                redirect grade
            }
            '*'{ respond grade, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        gradeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'grade.label', default: 'Grade'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'grade.label', default: 'Grade'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
