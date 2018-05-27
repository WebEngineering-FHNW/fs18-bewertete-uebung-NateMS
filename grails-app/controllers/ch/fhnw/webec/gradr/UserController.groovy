package ch.fhnw.webec.gradr

class UserController {

    def authenticate = {
        def user = User.findByEmailAndPassword(params.email, params.password)
        if (user) {
            session.user = user
            flash.message = "Hello ${user.name()}!"
            flash.class = "success"
            redirect(controller: "semester")
        } else {
            flash.message = "Sorry, ${params.email}. Please try again."
            flash.class = "error"
            redirect(controller: "home")
        }
    }

    def logout = {
        flash.message = "Goodbye ${session.user.name()}"
        flash.class = "success"
        session.user = null
        redirect(controller: "home")
    }
}
