package daoClasses;

import dao.GenericDAO;
import entity.Seats;
import entity.SeatsId;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SeatsDAO extends GenericDAO<Seats, SeatsId> {
    @SuppressWarnings("unchecked")
    public List<Seats> getByCompId(Long compId) {
        TypedQuery<Seats> query = getCurrentSession().createQuery(
                "SELECT e FROM Seats e " +
                        "WHERE e.id.compId = :compId")
                .setParameter("compId", compId);
        return query.getResultList();
    }
}
