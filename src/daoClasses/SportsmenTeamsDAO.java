package daoClasses;

import dao.GenericDAO;
import entity.SportsmenTeams;
import entity.SportsmenTeamsId;

import javax.persistence.TypedQuery;
import java.util.List;

public class SportsmenTeamsDAO extends GenericDAO<SportsmenTeams, SportsmenTeamsId> {
    public SportsmenTeams getByCompositeId(Long sportsmanId, Long teamId) {
        return getById(new SportsmenTeamsId(sportsmanId, teamId));
    }

    @SuppressWarnings("unchecked")
    public List<SportsmenTeams> getBySportsmenId(Long sportsmanId) {
        TypedQuery<SportsmenTeams> query = getSession().createQuery(
                "SELECT e FROM SportsmenTeams e " +
                        "WHERE e.id.sportsmanId = :sportsmanId")
                .setParameter("sportsmanId", sportsmanId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SportsmenTeams> getByTeamId(Long teamId) {
        TypedQuery<SportsmenTeams> query = getSession().createQuery(
                "SELECT e FROM SportsmenTeams e " +
                        "WHERE e.id.teamId = :teamId")
                .setParameter("teamId", teamId);
        return query.getResultList();
    }
}
