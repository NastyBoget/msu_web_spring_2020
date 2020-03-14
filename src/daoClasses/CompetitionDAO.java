package daoClasses;

import dao.GenericDAO;
import entity.Competition;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

public class CompetitionDAO extends GenericDAO<Competition, Long> {
    @SuppressWarnings("unchecked")
    public List<Competition> getByCompTime(Timestamp startTime, Timestamp endTime) {
        TypedQuery<Competition> query = getSession().createQuery(
                "SELECT e FROM Competition e " +
                        "WHERE e.compTime " +
                        "BETWEEN :startTime AND :endTime")
                .setParameter("startTime", startTime, TemporalType.TIMESTAMP)
                .setParameter("endTime", endTime, TemporalType.TIMESTAMP);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getByLocation(String location) {
        TypedQuery<Competition> query = getSession().createQuery(
                "SELECT e FROM Competition e " +
                        "WHERE LOWER(e.location) LIKE LOWER(:location)")
                .setParameter("location", '%' + location + '%');
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getBySportKind(String sportKind) {
        TypedQuery<Competition> query = getSession().createQuery(
                "SELECT e FROM Competition e " +
                        "WHERE LOWER(e.sportKind) LIKE LOWER(:sportKind)")
                .setParameter("sportKind", '%' + sportKind + '%');
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getByStatus(String status) {
        TypedQuery<Competition> query = getSession().createQuery(
                "SELECT e FROM Competition e " +
                        "WHERE LOWER(e.compStatus) LIKE LOWER(:status)")
                .setParameter("status", status);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getByFreeSeats() {
        TypedQuery<Competition> query = getSession().createQuery(
                "SELECT e FROM Competition e INNER JOIN Seats p " +
                        "ON e.compId = p.id.compId " +
                        " AND p.numFreeSeats > 0");
        return query.getResultList();
    }
}
