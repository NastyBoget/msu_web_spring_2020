package daoClasses;

import dao.GenericDAO;
import entity.Team;

import javax.persistence.TypedQuery;
import java.util.List;

public class TeamDAO extends GenericDAO<Team, Long> {
    @SuppressWarnings("unchecked")
    public List<Team> getByName(String name) {
        TypedQuery<Team> query = getSession().createQuery(
                "SELECT e FROM Team e " +
                        "WHERE LOWER(e.teamName) LIKE LOWER(:name)")
                .setParameter("name", '%' + name + '%');
        return query.getResultList();
    }
}
