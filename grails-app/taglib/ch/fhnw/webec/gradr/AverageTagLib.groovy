package ch.fhnw.webec.gradr

class AverageTagLib {
    def avg = { attrs ->
        if (!attrs.val || attrs.val == 0.0) {
            attrs.val = 'N/A'
        } else {
            if (attrs.pre) {
                attrs.val = attrs.pre + ' ' + attrs.val
            }

            if (attrs.post) {
                attrs.val = attrs.val + ' ' + attrs.post
            }
        }

        out << '<span class="average">' + attrs.val + '</span>'
    }
}
