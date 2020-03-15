package daoClasses;

import dao.GenericDAO;
import entity.CompSportsmen;
import entity.CompSportsmenId;

import javax.persistence.TypedQuery;
import java.util.List;

public class CompSportsmenDAO extends GenericDAO<CompSportsmen, CompSportsmenId> {
    public CompSportsmen getByCompositeId(Long compId, Long sportsmanId) {
        return getById(new CompSportsmenId(compId, sportsmanId));
    }

    @SuppressWarnings("unchecked")
    public List<CompSportsmen> getByCompId(Long compId) {
        TypedQuery<CompSportsmen> query = getSession().createQuery(
                "SELECT e FROM CompSportsmen e " +
                        "WHERE e.id.compId = :compId")
                .setParameter("compId", compId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<CompSportsmen> getBySportsmanId(Long sportsmanId) {
        TypedQuery<CompSportsmen> query = getSession().createQuery(
                "SELECT e FROM CompSportsmen e " +
                        "WHERE e.id.sportsmanId = :sportsmanId")
                .setParameter("sportsmanId", sportsmanId);
        return query.getResultList();
    }
}