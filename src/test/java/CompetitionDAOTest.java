import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import static org.testng.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import daoClasses.CompetitionDAO;
import entity.Competition;

public class CompetitionDAOTest {
    private CompetitionDAO _dao;
    private Session s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory().openSession();
        tx = s.beginTransaction();
        _dao = new CompetitionDAO();
        _dao.setSession(s);
    }

    public void tearDown() throws Exception {
        tx.commit();
    }

    @Test
    public void getById() throws Exception {
        setUp();
        assertNull(_dao.getById((long) -1));
        tearDown();
    }

    @Test
    public void saveUpdateDelete() throws Exception {
        setUp();
        Competition tmp = new Competition();
        tmp.setCompId((long) 6);
        tmp.setCompName("New competition");
        tmp.setCompStatus(Competition.CompStatus.now);
        tmp.setCompTime(new Timestamp(2020, 3, 31, 10, 0, 0, 0));
        tmp.setLocation("Russia, Moscow, Arena CSKA");
        tmp.setSportKind(Competition.SportKind.football);
        tmp.setFreeSeatsStatus(Boolean.TRUE);
        _dao.save(tmp);
        assertEquals("New competition", _dao.getById((long) 6).getCompName());
        assertEquals("now", _dao.getById((long) 6).getCompStatus());
        assertEquals(new Timestamp(2020, 3, 31, 10, 0, 0, 0),
                _dao.getById((long) -1).getCompTime());
        assertEquals("Russia, Moscow, Arena CSKA", _dao.getById((long) -1).getLocation());
        assertEquals("football", _dao.getById((long) 6).getSportKind());
        assertEquals(Boolean.TRUE, _dao.getById((long) 6).isFreeSeatsStatus());
        tmp.setCompName("New competition name");
        _dao.update(tmp);
        assertEquals("New competition name",  _dao.getById((long)-1).getCompName());
        // ...
        _dao.delete(tmp);
        assertNull(_dao.getById((long) 6));
        tearDown();
    }

    @Test
    public void findAll() throws Exception {
        setUp();
        List<Competition> _list = _dao.getAll();
        for (Competition i: _list) {
            assertEquals(i.getCompName(), _dao.getById(i.getCompId()).getCompName());
            assertEquals(i.getCompStatus(), _dao.getById(i.getCompId()).getCompStatus());
            assertEquals(i.getCompTime(), _dao.getById(i.getCompId()).getCompTime());
            assertEquals(i.getLocation(), _dao.getById(i.getCompId()).getLocation());
            assertEquals(i.getSportKind(), _dao.getById(i.getCompId()).getSportKind());
            assertEquals(i.isFreeSeatsStatus(), _dao.getById(i.getCompId()).isFreeSeatsStatus());
        }
        tearDown();
    }
}
