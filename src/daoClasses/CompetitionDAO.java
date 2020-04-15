package daoClasses;

import dao.GenericDAO;
import entity.Competition;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public class CompetitionDAO extends GenericDAO<Competition, Long> {
    @SuppressWarnings("unchecked")
    public List<Competition> getByCompTime(Timestamp startTime, Timestamp endTime) {
        TypedQuery<Competition> query = getCurrentSession().createQuery(
                "SELECT e FROM Competition e " +
                        "WHERE e.compTime " +
                        "BETWEEN :startTime AND :endTime")
                .setParameter("startTime", startTime, TemporalType.TIMESTAMP)
                .setParameter("endTime", endTime, TemporalType.TIMESTAMP);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getByLocation(String location) {
        TypedQuery<Competition> query = getCurrentSession().createQuery(
                "SELECT e FROM Competition e " +
                        "WHERE LOWER(e.location) LIKE LOWER(:location)")
                .setParameter("location", '%' + location + '%');
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getBySportKind(Competition.SportKind sportKind) {
        TypedQuery<Competition> query = getCurrentSession().createQuery(
                "SELECT e FROM Competition e " +
                        "WHERE e.sportKind = :sportKind")
                .setParameter("sportKind", sportKind);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getByStatus(Competition.CompStatus status) {
        TypedQuery<Competition> query = getCurrentSession().createQuery(
                "SELECT e FROM Competition e " +
                        "WHERE e.compStatus = :status")
                .setParameter("status", status);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getByFreeSeats() {
        TypedQuery<Competition> query = getCurrentSession().createQuery(
                "SELECT e FROM Competition e INNER JOIN Seats p " +
                        "ON e.compId = p.id.compId " +
                        " AND p.numFreeSeats > 0");
        return query.getResultList();
    }
}
