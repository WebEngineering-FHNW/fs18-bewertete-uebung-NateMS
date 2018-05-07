package ch.fhnw.webec.gradr

class Grade {

    static belongsTo = [ course:Course ]

    int weight
    float grade
    boolean isFinal

    static constraints = {
    }
}
