package daoClasses;

import dao.GenericDAO;
import entity.CompSportsmen;
import entity.CompSportsmenId;
import entity.Competition;
import entity.Sportsman;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompSportsmenDAO extends GenericDAO<CompSportsmen, CompSportsmenId> {
    public CompSportsmen getByCompositeId(Long compId, Long sportsmanId) {
        return getById(new CompSportsmenId(compId, sportsmanId));
    }

    @SuppressWarnings("unchecked")
    public List<Sportsman> getByCompId(Long compId) {
        TypedQuery<Sportsman> query = getCurrentSession().createQuery(
                "SELECT e.sportsmanId FROM CompSportsmen e " +
                        "WHERE e.id.compId = :compId")
                .setParameter("compId", compId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getBySportsmanId(Long sportsmanId) {
        TypedQuery<Competition> query = getCurrentSession().createQuery(
                "SELECT e.compId FROM CompSportsmen e " +
                        "WHERE e.id.sportsmanId = :sportsmanId")
                .setParameter("sportsmanId", sportsmanId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<CompSportsmen> getAllByCompId(Long compId) {
        TypedQuery<CompSportsmen> query = getCurrentSession().createQuery(
                "SELECT e FROM CompSportsmen e " +
                        "WHERE e.id.compId = :compId")
                .setParameter("compId", compId);
        return query.getResultList();
    }
}
