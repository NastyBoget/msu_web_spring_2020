package test;

import daoClasses.CompTeamsDAO;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

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
        List<CompTeams> _list = _dao.getByCompId((long) 5);
        assertEquals(2, _list.size());
        for (CompTeams i: _list) {
            assertNull(i.getPlace());
            assertNull(i.getPoints());
            assertEquals(5, (long) i.getCompId().getCompId());
        }

        _list = _dao.getByTeamId((long) 2);
        assertEquals(1, _list.size());
        for (CompTeams i: _list) {
            assertEquals(3, (int) i.getPlace());
            assertEquals(10, (int) i.getPoints());
            assertEquals(2, (long) i.getTeamId().getTeamId());
        }

        CompTeams _entity = _dao.getByCompositeId((long) 2, (long) 1);
        assertNull(_entity.getPoints());
        assertNull(_entity.getPlace());
        assertEquals(2, (long) _entity.getCompId().getCompId());
        assertEquals(1, (long) _entity.getTeamId().getTeamId());

        _list = _dao.getByCompId((long) 100);
        assertTrue(_list.isEmpty());
        _list = _dao.getByTeamId((long) 100);
        assertTrue(_list.isEmpty());
        assertNull(_dao.getByCompositeId((long) 100, (long) 100));
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