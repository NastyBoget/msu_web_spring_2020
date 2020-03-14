package daoClasses;

import dao.GenericDAO;
import entity.Seats;
import entity.SeatsId;

import javax.persistence.TypedQuery;
import java.util.List;

public class SeatsDAO extends GenericDAO<Seats, SeatsId> {
    @SuppressWarnings("unchecked")
    public List<Seats> getByCompId(Long compId) {
        TypedQuery<Seats> query = getSession().createQuery(
                "SELECT e FROM Seats e " +
                        "WHERE e.compId = :compId")
                .setParameter("compId", compId);
        return query.getResultList();
    }
}
