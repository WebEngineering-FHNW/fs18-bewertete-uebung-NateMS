package ch.fhnw.webec.gradr


class AuthInterceptor {
    AuthInterceptor() {
        matchAll()
                .excludes(controller: 'home')
                .excludes(controller: 'user', action: 'authenticate')
    }

    boolean before() {
        if (!session.user) {
            redirect controller: 'home'
            false
        }
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
