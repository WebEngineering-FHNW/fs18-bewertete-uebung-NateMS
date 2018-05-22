package ch.fhnw.webec.gradr

class AverageTagLib {
    def avg = { attrs ->

        def cl = 'grade'

        if (!attrs.val || attrs.val == 0.0) {
            attrs.val = 'N/A'
        } else if (attrs.val < 4.0) {
            cl = cl + ' failing'
        }

        attrs.val = '<span class="' + cl + '">' + attrs.val + '</span>'

        if (attrs.pre) {
            attrs.pre = '<span class="pre">' + attrs.pre + '</span>'
        } else {
            attrs.pre = '';
        }

        if (attrs.post) {
            attrs.post = '<span class="post">' + attrs.post + '</span>'
        } else {
            attrs.post = '';
        }

        out << '<span class="average">' + attrs.pre + '' + attrs.val + '' + attrs.post + '</span>'
    }
}
