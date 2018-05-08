package ch.fhnw.webec.gradr

class SemesterController {

    static scaffold = Semester

    def beforeInterceptor = [action:this.&auth]

    def auth() {
        if(!session.user) {
            redirect(controller:"home")
            return false
        }
    }
}
