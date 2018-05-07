package ch.fhnw.webec.gradr

class HomeController {

    def index() {
        respond([name: session.name ?: 'Guest', courseTotal: Course.count()])
    }

    def updateName(String name) {
        session.name = name

        flash.message = "Name has been updated"

        redirect action: 'index'
    }

}
