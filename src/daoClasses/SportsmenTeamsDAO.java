package daoClasses;

import dao.GenericDAO;
import entity.Sportsman;
import entity.SportsmenTeams;
import entity.SportsmenTeamsId;
import entity.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SportsmenTeamsDAO extends GenericDAO<SportsmenTeams, SportsmenTeamsId> {
    public SportsmenTeams getByCompositeId(Long sportsmanId, Long teamId) {
        return getById(new SportsmenTeamsId(sportsmanId, teamId));
    }

    @SuppressWarnings("unchecked")
    public List<Team> getBySportsmenId(Long sportsmanId) {
        TypedQuery<Team> query = getCurrentSession().createQuery(
                "SELECT e.teamId FROM SportsmenTeams e " +
                        "WHERE e.id.sportsmanId = :sportsmanId")
                .setParameter("sportsmanId", sportsmanId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Sportsman> getByTeamId(Long teamId) {
        TypedQuery<Sportsman> query = getCurrentSession().createQuery(
                "SELECT e.sportsmanId FROM SportsmenTeams e " +
                        "WHERE e.id.teamId = :teamId")
                .setParameter("teamId", teamId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SportsmenTeams> getAllByTeamId(Long teamId) {
        TypedQuery<SportsmenTeams> query = getCurrentSession().createQuery(
                "SELECT e FROM SportsmenTeams e " +
                        "WHERE e.id.teamId = :teamId")
                .setParameter("teamId", teamId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SportsmenTeams> getAllBySportsmanId(Long sportsmanId) {
        TypedQuery<SportsmenTeams> query = getCurrentSession().createQuery(
                "SELECT e FROM SportsmenTeams e " +
                        "WHERE e.id.sportsmanId = :sportsmanId")
                .setParameter("sportsmanId", sportsmanId);
        return query.getResultList();
    }
}
