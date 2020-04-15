package test;

import daoClasses.SportsmenTeamsDAO;
import entity.Sportsman;
import entity.SportsmenTeams;
import entity.Team;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SportsmenTeamsDAOTest {
    private SportsmenTeamsDAO _dao;
    private SessionFactory s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory();
        tx = s.getCurrentSession().beginTransaction();
        _dao = new SportsmenTeamsDAO();
        _dao.setSessionFactory(s);
    }

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    void getSportsmenTeams() throws Exception {
        setUp();
        List<Team> teamList = _dao.getBySportsmenId((long) 8);
        assertEquals(2, teamList.size());
        for (Team i: teamList) {
            assertNotNull(i);
        }

        List<Sportsman> sportsmanList = _dao.getByTeamId((long) 4);
        assertEquals(1, sportsmanList.size());
        for (Sportsman i: sportsmanList) {
            assertEquals(5, (long) i.getTeamId().getTeamId());
        }

        SportsmenTeams _entity = _dao.getByCompositeId((long) 3, (long) 1);
        assertEquals(3, (long) _entity.getSportsmanId().getSportsmanId());
        assertEquals(1, (long) _entity.getTeamId().getTeamId());

        teamList = _dao.getBySportsmenId((long) 100);
        assertTrue(teamList.isEmpty());
        sportsmanList = _dao.getByTeamId((long) 100);
        assertTrue(sportsmanList.isEmpty());
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
