package test;

import daoClasses.TeamDAO;
import daoClasses.TrainerDAO;
import entity.Team;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TeamDAOTest {
    private TeamDAO _dao;
    private TrainerDAO _dao_trainer;
    private Session s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory().openSession();
        tx = s.beginTransaction();
        _dao = new TeamDAO();
        _dao_trainer = new TrainerDAO();
        _dao.setSession(s);
        _dao_trainer.setSession(s);
    }

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    public void getTrainer() throws Exception {
        setUp();
        assertNull(_dao.getById((long) -1));

        Team team = _dao.getById((long) 1);
        assertEquals(team.getTeamName(), "Vegas Golden Knights");
        assertEquals(1, (long) team.getTeamId());
        assertEquals(3, (long) team.getTrainerId().getTrainerId());

        List<Team> _list = _dao.getByName("Vegas Golden Knights");
        assertEquals(1, _list.size());
        for (Team t: _list) {
            assertEquals(t.getTeamName(), "Vegas Golden Knights");
            assertEquals(1, (long) t.getTeamId());
            assertEquals(3, (long) t.getTrainerId().getTrainerId());
        }
        assertTrue(_dao.getByName("jgfvhgcfhg").isEmpty());
        shutDown();
    }

    @Test(priority = 1)
    public void saveUpdateDelete() throws Exception {
        setUp();
        Team tmp = new Team();
        tmp.setTeamName("Dinamo");
        tmp.setTrainerId(_dao_trainer.getById((long) 2));
        _dao.save(tmp);
        assertEquals("Dinamo", _dao.getById(tmp.getTeamId()).getTeamName());
        assertEquals(_dao_trainer.getById((long) 2), _dao.getById(tmp.getTeamId()).getTrainerId());

        tmp.setTeamName("New name");
        _dao.update(tmp);
        assertEquals("New name", _dao.getById(tmp.getTeamId()).getTeamName());
        tmp.setTrainerId(_dao_trainer.getById((long) 1));
        _dao.update(tmp);
        assertEquals(_dao_trainer.getById((long) 1), _dao.getById(tmp.getTeamId()).getTrainerId());

        _dao.delete(tmp);
        assertNull(_dao.getById(tmp.getTeamId()));
        shutDown();
    }

    @Test(priority = 2)
    public void findAll() throws Exception {
        setUp();
        List<Team> _list = _dao.getAll();
        for (Team i: _list) {
            assertEquals(i.getTeamId(), _dao.getById(i.getTeamId()).getTeamId());
            assertEquals(i.getTeamName(), _dao.getById(i.getTeamId()).getTeamName());
            assertEquals(i.getTrainerId(), _dao.getById(i.getTeamId()).getTrainerId());
        }
        shutDown();
    }
}
