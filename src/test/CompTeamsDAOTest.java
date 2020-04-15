package test;

import daoClasses.CompTeamsDAO;
import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CompTeamsDAOTest {
    private CompTeamsDAO _dao;
    private SessionFactory s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory();
        tx = s.getCurrentSession().beginTransaction();
        _dao = new CompTeamsDAO();
        _dao.setSessionFactory(s);
    }

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    void getCompTeams() throws Exception {
        setUp();
        List<Team> teamList = _dao.getByCompId((long) 5);
        assertEquals(2, teamList.size());
        for (Team i: teamList) {
            assertNotNull(i);
        }

        List<Competition> compList = _dao.getByTeamId((long) 2);
        assertEquals(1, compList.size());
        for (Competition i: compList) {
            assertEquals(1, (long) i.getCompId());
            assertEquals("FIFA World Cup", i.getCompName());
            assertEquals("Russia, Moscow, Arena CSKA", i.getLocation());
        }

        CompTeams _entity = _dao.getByCompositeId((long) 2, (long) 1);
        assertNull(_entity.getPoints());
        assertNull(_entity.getPlace());
        assertEquals(2, (long) _entity.getCompId().getCompId());
        assertEquals(1, (long) _entity.getTeamId().getTeamId());

        teamList = _dao.getByCompId((long) 100);
        assertTrue(teamList.isEmpty());
        compList = _dao.getByTeamId((long) 100);
        assertTrue(compList.isEmpty());
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