package test;

import daoClasses.SportsmanDAO;
import daoClasses.TeamDAO;
import daoClasses.TrainerDAO;
import entity.Sportsman;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

public class SportsmanDAOTest {
    private SportsmanDAO _dao;
    private TrainerDAO _dao_trainer;
    private TeamDAO _dao_team;
    private Session s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory().openSession();
        tx = s.beginTransaction();
        _dao = new SportsmanDAO();
        _dao_trainer = new TrainerDAO();
        _dao_team = new TeamDAO();
        _dao.setSession(s);
        _dao_trainer.setSession(s);
        _dao_team.setSession(s);
    }

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    public void getSportsman() throws Exception {
        setUp();
        assertNull(_dao.getById((long) -1));
        Sportsman sportsman = _dao.getById((long) 1);
        assertEquals(1, (long) sportsman.getTrainerId().getTrainerId());
        assertEquals("David Pastrniak", sportsman.getSportsmanName());
        assertEquals("1980-03-21 00:00:00.0", sportsman.getBirthday().toString());
        assertNull(sportsman.getTeamId());
        assertEquals(1, (long) sportsman.getSportsmanId());

        List<Sportsman> _list = _dao.getByName("David Pastrniak");
        assertEquals(1, _list.size());
        for (Sportsman s: _list) {
            assertEquals(1, (long) s.getTrainerId().getTrainerId());
            assertEquals("1980-03-21 00:00:00.0", s.getBirthday().toString());
            assertNull(s.getTeamId());
            assertEquals(1, (long) s.getSportsmanId());
        }
        assertTrue(_dao.getByName("jgfvhgcfhg").isEmpty());
        shutDown();
    }

    @Test(priority = 1)
    public void saveUpdateDelete() throws Exception {
        setUp();
        Sportsman sportsman = new Sportsman();
        sportsman.setSportsmanId((long) 11);
        sportsman.setSportsmanName("Daniel Malygin");
        sportsman.setBirthday(new Date(100, 1, 24));
        sportsman.setTeamId(null);
        sportsman.setTrainerId(_dao_trainer.getById((long) 1));

        _dao.save(sportsman);
        assertEquals(11, (long) sportsman.getSportsmanId());
        assertEquals("Daniel Malygin", sportsman.getSportsmanName());
        assertEquals(new Date(100, 1, 24), sportsman.getBirthday());
        assertNull(sportsman.getTeamId());
        assertEquals(_dao_trainer.getById((long) 1), sportsman.getTrainerId());

        sportsman.setSportsmanName("Raenchuk Maxim");
        _dao.update(sportsman);
        assertEquals("Raenchuk Maxim", sportsman.getSportsmanName());
        sportsman.setBirthday(new Date(100, 1, 9));
        _dao.update(sportsman);
        assertEquals(new Date(100, 1, 9), sportsman.getBirthday());
        sportsman.setTeamId(_dao_team.getById((long) 1));
        _dao.update(sportsman);
        assertEquals(_dao_team.getById((long) 1), sportsman.getTeamId());
        sportsman.setTrainerId(_dao_trainer.getById((long) 2));
        _dao.update(sportsman);
        assertEquals(_dao_trainer.getById((long) 2), sportsman.getTrainerId());

        _dao.delete(sportsman);
        assertNull(_dao.getById(sportsman.getSportsmanId()));
        shutDown();
    }

    @Test(priority = 2)
    public void findAll() throws Exception {
        setUp();
        List<Sportsman> _list = _dao.getAll();
        for (Sportsman i: _list) {
            assertEquals(i.getSportsmanId(), _dao.getById(i.getSportsmanId()).getSportsmanId());
            assertEquals(i.getTeamId(), _dao.getById(i.getSportsmanId()).getTeamId());
            assertEquals(i.getBirthday(), _dao.getById(i.getSportsmanId()).getBirthday());
            assertEquals(i.getSportsmanName(), _dao.getById(i.getSportsmanId()).getSportsmanName());
            assertEquals(i.getTrainerId(), _dao.getById(i.getSportsmanId()).getTrainerId());
        }
        shutDown();
    }
}
