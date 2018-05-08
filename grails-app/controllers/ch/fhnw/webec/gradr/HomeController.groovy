package ch.fhnw.webec.gradr

class HomeController {

    def index() {
        respond([name: session.user?.name() ?: 'Guest'])
    }
}
