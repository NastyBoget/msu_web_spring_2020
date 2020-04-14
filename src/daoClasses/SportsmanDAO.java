package daoClasses;

import dao.GenericDAO;
import entity.Sportsman;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SportsmanDAO extends GenericDAO<Sportsman, Long> {
    @SuppressWarnings("unchecked")
    public List<Sportsman> getByName(String name) {
        TypedQuery<Sportsman> query = getCurrentSession().createQuery(
                "SELECT e FROM Sportsman e " +
                        "WHERE LOWER(e.sportsmanName) LIKE LOWER(:name)")
                .setParameter("name", '%' + name + '%');
        return query.getResultList();
    }
}
