package daoClasses;

import dao.GenericDAO;
import entity.CompTeams;
import entity.CompTeamsId;
import entity.Competition;
import entity.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompTeamsDAO extends GenericDAO<CompTeams, CompTeamsId> {
    public CompTeams getByCompositeId(Long compId, Long teamId) {
        return getById(new CompTeamsId(compId, teamId));
    }

    @SuppressWarnings("unchecked")
    public List<Team> getByCompId(Long compId) {
        TypedQuery<Team> query = getCurrentSession().createQuery(
                "SELECT e.teamId FROM CompTeams e " +
                        "WHERE e.id.compId = :compId")
                .setParameter("compId", compId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getByTeamId(Long teamId) {
        TypedQuery<Competition> query = getCurrentSession().createQuery(
                "SELECT e.compId FROM CompTeams e " +
                        "WHERE e.id.teamId = :teamId")
                .setParameter("teamId", teamId);
        return query.getResultList();
    }
}
