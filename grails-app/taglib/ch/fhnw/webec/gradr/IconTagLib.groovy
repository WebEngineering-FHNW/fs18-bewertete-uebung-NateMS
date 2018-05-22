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

        out << '<img class="' + className + '" src="' + path + '"/>'
    }

    def logo = { attrs ->

        String className = "svg-logo"

        if (attrs.class) {
            className = className + ' ' + attrs.class
        }

        out << '<img class="' + className + '" src="/static/logo.svg"/>'
    }

    def submit = { attrs ->
        out << '<input class="delete icon icon-delete icon-submit" type="image" src="/static/icons/' + iconPack + '/trash.svg" onclick="return confirm(\'Are you sure?\');"/>'
    }
}
