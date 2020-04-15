package test;

import daoClasses.CompSportsmenDAO;
import entity.CompSportsmen;
import entity.Competition;
import entity.Sportsman;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CompSportsmenDAOTest {
    private CompSportsmenDAO _dao;
    private SessionFactory s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory();
        tx = s.getCurrentSession().beginTransaction();
        _dao = new CompSportsmenDAO();
        _dao.setSessionFactory(s);
    }

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    void getCompSportsmen() throws Exception {
        setUp();
        List<Sportsman> sportsmanList = _dao.getByCompId((long) 4);
        assertEquals(3, sportsmanList.size());
        for (Sportsman i: sportsmanList) {
            assertNotNull(i);
        }

        List<Competition> compList = _dao.getBySportsmanId((long) 6);
        assertEquals(1, compList.size());
        for (Competition i: compList) {
            assertEquals(3, (long) i.getCompId());
            assertEquals("2020 GYMNASTICS WORLD CUP", i.getCompName());
            assertEquals("UK, Birmingham, Arena Birmingham", i.getLocation());
        }

        CompSportsmen _entity = _dao.getByCompositeId((long) 4, (long) 10);
        assertNull(_entity.getPoints());
        assertNull(_entity.getPlace());
        assertEquals(4, (long) _entity.getCompId().getCompId());
        assertEquals(10, (long) _entity.getSportsmanId().getSportsmanId());

        sportsmanList = _dao.getByCompId((long) 100);
        assertTrue(sportsmanList.isEmpty());
        compList = _dao.getBySportsmanId((long) 100);
        assertTrue(compList.isEmpty());
        assertNull(_dao.getByCompositeId((long) 100, (long) 100));
        shutDown();
    }

    @Test(priority = 2)
    public void findAll() throws Exception {
        setUp();
        List<CompSportsmen> _list = _dao.getAll();
        for (CompSportsmen i: _list) {
            assertEquals(i.getCompId(), _dao.getById(i.getId()).getCompId());
            assertEquals(i.getSportsmanId(), _dao.getById(i.getId()).getSportsmanId());
            assertEquals(i.getPlace(), _dao.getById(i.getId()).getPlace());
            assertEquals(i.getPoints(), _dao.getById(i.getId()).getPoints());
        }
        shutDown();
    }
}