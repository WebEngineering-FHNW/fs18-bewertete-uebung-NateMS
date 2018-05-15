package ch.fhnw.webec.gradr

class UserController {

    static scaffold = User

    def authenticate = {
        def user = User.findByEmailAndPassword(params.email, params.password)
        if (user) {
            session.user = user
            flash.message = "Hello ${user.name()}!"
            redirect(controller: "semester" action: "index")
        } else {
            flash.message = "Sorry, ${params.email}. Please try again."
            redirect(controller: "home")
        }
    }

    def logout = {
        flash.message = "Goodbye ${session.user.name()}"
        session.user = null
        redirect(controller: "home")
    }
}
