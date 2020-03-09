package daoClasses;

import dao.GenericDAO;
import entity.CompTeams;
import entity.CompTeamsId;

import javax.persistence.TypedQuery;
import java.util.List;

public class CompTeamsDAO extends GenericDAO<CompTeams, CompTeamsId> {
    public CompTeams getByCompositeId(Long compId, Long teamId) {
        return getById(new CompTeamsId(compId, teamId));
    }

    @SuppressWarnings("unchecked")
    public List<CompTeams> getByCompId(Long compId) {
        TypedQuery<CompTeams> query = getSession().createQuery(
                "SELECT e FROM CompTeams e " +
                        "WHERE e.id.compId = :compId")
                .setParameter("compId", compId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<CompTeams> getByTeamId(Long teamId) {
        TypedQuery<CompTeams> query = getSession().createQuery(
                "SELECT e FROM CompTeams e " +
                        "WHERE e.id.teamId = :teamId")
                .setParameter("teamId", teamId);
        return query.getResultList();
    }
}
