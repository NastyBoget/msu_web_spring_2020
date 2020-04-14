package daoClasses;

import dao.GenericDAO;
import entity.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TeamDAO extends GenericDAO<Team, Long> {
    @SuppressWarnings("unchecked")
    public List<Team> getByName(String name) {
        TypedQuery<Team> query = getCurrentSession().createQuery(
                "SELECT e FROM Team e " +
                        "WHERE LOWER(e.teamName) LIKE LOWER(:name)")
                .setParameter("name", '%' + name + '%');
        return query.getResultList();
    }

}
