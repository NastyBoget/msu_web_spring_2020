import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import daoClasses.CompetitionDAO;
import entity.Competition;

// https://habr.com/ru/post/121234/

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

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    public void getCompetition() throws Exception {
        setUp();
        assertNull(_dao.getById((long) -1));
        Competition competition = _dao.getById((long) 1);
        assertEquals(competition.getCompName(), "FIFA World Cup");
        assertEquals(competition.getCompStatus(), Competition.CompStatus.passed);
        assertEquals(competition.getCompTime().toString(), "2019-09-22 17:20:00.0");
        assertEquals(competition.getLocation(), "Russia, Moscow, Arena CSKA");
        assertEquals(competition.getSportKind(), Competition.SportKind.football);
        assertFalse(competition.isFreeSeatsStatus());

        List<Competition> comps = _dao.getByCompTime(new Timestamp(2019, 9, 22, 17, 20, 0, 0),
                new Timestamp(2019, 9, 22, 17, 20, 0, 0));
        for (Competition comp: comps) {
            assertEquals((long) comp.getCompId(), 1);
        }

        comps = _dao.getByLocation("FIFA World Cup");
        for (Competition comp: comps) {
            assertEquals((long) comp.getCompId(), 1);
        }

        comps = _dao.getBySportKind(Competition.SportKind.football);
        for (Competition comp: comps) {
            assertEquals((long) comp.getCompId(), 1);
        }

        List<Competition> testComp = Arrays.asList(
                _dao.getById((long) 1),
                _dao.getById((long) 3)
        );
        comps = _dao.getByStatus(Competition.CompStatus.passed);
        for (Competition comp: comps) {
            assertTrue(testComp.contains(comp));
        }

        testComp = Arrays.asList(
                _dao.getById((long) 3),
                _dao.getById((long) 4),
                _dao.getById((long) 5)
        );
        comps = _dao.getByFreeSeats();
        for (Competition comp: comps) {
            assertTrue(testComp.contains(comp));
        }

        assertTrue(_dao.getByLocation("kgjfjhf").isEmpty());
        assertTrue(_dao.getByCompTime(new Timestamp(0, 0, 0, 0, 0, 0, 0),
                new Timestamp(0, 0, 0, 0, 0, 0, 0)).isEmpty());

        shutDown();
    }

    @Test(priority = 1)
    public void saveUpdateDelete() throws Exception {
        setUp();
        Competition tmp = new Competition();
        tmp.setCompName("New competition");
        tmp.setCompStatus(Competition.CompStatus.now);
        tmp.setCompTime(new Timestamp(2020, 3, 31, 10, 0, 0, 0));
        tmp.setLocation("Russia, Moscow, Arena CSKA");
        tmp.setSportKind(Competition.SportKind.biathlon);
        tmp.setFreeSeatsStatus(Boolean.TRUE);
        _dao.save(tmp);
        assertEquals("New competition", _dao.getById(tmp.getCompId()).getCompName());
        assertEquals(Competition.CompStatus.now, _dao.getById(tmp.getCompId()).getCompStatus());
        assertEquals(new Timestamp(2020, 3, 31, 10, 0, 0, 0),
                _dao.getById(tmp.getCompId()).getCompTime());
        assertEquals("Russia, Moscow, Arena CSKA", _dao.getById(tmp.getCompId()).getLocation());
        assertEquals(Competition.SportKind.biathlon, _dao.getById(tmp.getCompId()).getSportKind());
        assertEquals(Boolean.TRUE, _dao.getById(tmp.getCompId()).isFreeSeatsStatus());

        tmp.setCompName("New competition name");
        _dao.update(tmp);
        assertEquals("New competition name",  _dao.getById(tmp.getCompId()).getCompName());
        tmp.setCompStatus(Competition.CompStatus.soon);
        _dao.update(tmp);
        assertEquals(Competition.CompStatus.soon, _dao.getById(tmp.getCompId()).getCompStatus());
        tmp.setCompTime(new Timestamp(2020, 4, 30, 10, 0, 0, 0));
        _dao.update(tmp);
        assertEquals(new Timestamp(2020, 4, 30, 10, 0, 0, 0),
                _dao.getById(tmp.getCompId()).getCompTime());
        tmp.setLocation("Russia, Moscow");
        _dao.update(tmp);
        assertEquals("Russia, Moscow", _dao.getById(tmp.getCompId()).getLocation());
        tmp.setSportKind(Competition.SportKind.hockey);
        _dao.update(tmp);
        assertEquals(Competition.SportKind.hockey, _dao.getById(tmp.getCompId()).getSportKind());
        tmp.setFreeSeatsStatus(Boolean.FALSE);
        _dao.update(tmp);
        assertEquals(Boolean.FALSE, _dao.getById(tmp.getCompId()).isFreeSeatsStatus());

        _dao.delete(tmp);
        assertNull(_dao.getById(tmp.getCompId()));
        shutDown();
    }

    @Test(priority = 2)
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
        shutDown();
    }
}
