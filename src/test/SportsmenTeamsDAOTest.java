package test;

import daoClasses.SportsmenTeamsDAO;
import entity.SportsmenTeams;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SportsmenTeamsDAOTest {
    private SportsmenTeamsDAO _dao;
    private Session s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory().openSession();
        tx = s.beginTransaction();
        _dao = new SportsmenTeamsDAO();
        _dao.setSession(s);
    }

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    void getSportsmenTeams() throws Exception {
        setUp();
        List<SportsmenTeams> _list = _dao.getBySportsmenId((long) 8);
        assertEquals(2, _list.size());
        for (SportsmenTeams i: _list) {
            assertEquals(8, (long) i.getSportsmanId().getSportsmanId());
        }

        _list = _dao.getByTeamId((long) 4);
        assertEquals(1, _list.size());
        for (SportsmenTeams i: _list) {
            assertEquals(4, (long) i.getTeamId().getTeamId());
        }

        SportsmenTeams _entity = _dao.getByCompositeId((long) 3, (long) 1);
        assertEquals(3, (long) _entity.getSportsmanId().getSportsmanId());
        assertEquals(1, (long) _entity.getTeamId().getTeamId());

        _list = _dao.getBySportsmenId((long) 100);
        assertTrue(_list.isEmpty());
        _list = _dao.getByTeamId((long) 100);
        assertTrue(_list.isEmpty());
        assertNull(_dao.getByCompositeId((long) 100, (long) 100));
        shutDown();
    }

    @Test(priority = 2)
    public void findAll() throws Exception {
        setUp();
        List<SportsmenTeams> _list = _dao.getAll();
        for (SportsmenTeams i: _list) {
            assertEquals(i.getSportsmanId(), _dao.getById(i.getId()).getSportsmanId());
            assertEquals(i.getTeamId(), _dao.getById(i.getId()).getTeamId());
        }
        shutDown();
    }
}
