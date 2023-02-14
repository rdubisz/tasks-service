package net.rd.tasks.service.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ReportingQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Double taskOperationQuery(LocalDateTime startTime, LocalDateTime endTime, Long taskDefinitionId) {
        String queryString = "SELECT AVG(r.duration) AS resultValue "
                + "FROM TaskOperationEntity r "
                + "WHERE r.startTime >= :startTime "
                + "AND r.startTime <= :endTime ";
        if(taskDefinitionId != null)
                queryString += "AND r.taskDefinition.id IN (:taskDefinitionId) ";

        TypedQuery<Double> query = entityManager.createQuery(queryString, Double.class);

        query.setParameter("startTime", startTime);
        query.setParameter("endTime", endTime);
        if(taskDefinitionId != null)
            query.setParameter("taskDefinitionId", taskDefinitionId);

        Double result = query.getSingleResult();
        return result;
    }

}
