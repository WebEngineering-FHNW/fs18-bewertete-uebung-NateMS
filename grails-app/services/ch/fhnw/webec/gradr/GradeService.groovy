package ch.fhnw.webec.gradr

import grails.gorm.services.Service

@Service(Grade)
interface GradeService {

    Grade get(Serializable id)

    List<Grade> list(Map args)

    Long count()

    void delete(Serializable id)

    Grade save(Grade grade)

}