package ch.fhnw.webec.gradr

class IconTagLib {
    String iconPack = 'ios'

    def icon = { attrs ->
        if (!attrs.name) {
            attrs.name = 'info'
        }
        String path = "/static/icons/$iconPack/$attrs.name" + ".svg"

        String className = "icon icon-$attrs.name"
        if (attrs.class) {
            className = className + ' ' + attrs.class
        }

        out << '<object type="image/svg+xml" class="' + className + '" data="' + path + '"></object>'
    }

    def logo = { attrs ->

        String className = "svg-logo"

        if (attrs.class) {
            className = className + ' ' + attrs.class
        }

        out << '<object type="image/svg+xml" class="' + className + '" data="/static/logo.svg"></object>'
    }
}
