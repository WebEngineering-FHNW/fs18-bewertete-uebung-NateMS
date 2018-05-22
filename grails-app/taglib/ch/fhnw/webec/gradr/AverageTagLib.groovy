package ch.fhnw.webec.gradr

class AverageTagLib {
    def avg = { attrs ->

        if (!attrs.val || attrs.val == 0.0) {
            attrs.val = 'N/A'
        }

        attrs.val = '<span class="grade">' + attrs.val + '</span>'

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
