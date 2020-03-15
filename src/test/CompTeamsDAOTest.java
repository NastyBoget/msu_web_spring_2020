import daoClasses.CompTeamsDAO;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CompTeamsDAOTest {
    private CompTeamsDAO _dao;
    private Session s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory().openSession();
        tx = s.beginTransaction();
        _dao = new CompTeamsDAO();
        _dao.setSession(s);
    }

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    void getCompTeams() throws Exception {
        setUp();

        shutDown();
    }

    @Test(priority = 1)
    void saveUpdateDelete() throws Exception {
        setUp();

        shutDown();
    }

    @Test(priority = 2)
    public void findAll() throws Exception {
        setUp();
        List<CompTeams> _list = _dao.getAll();
        for (CompTeams i: _list) {
            assertEquals(i.getCompId(), _dao.getById(i.getId()).getCompId());
            assertEquals(i.getTeamId(), _dao.getById(i.getId()).getTeamId());
            assertEquals(i.getPlace(), _dao.getById(i.getId()).getPlace());
            assertEquals(i.getPoints(), _dao.getById(i.getId()).getPoints());
        }
        shutDown();
    }
}