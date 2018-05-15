package ch.fhnw.webec.gradr

class HomeController {

    def index() {
        if (session.user) {
            redirect(controller: "semester")
        }
    }
}
