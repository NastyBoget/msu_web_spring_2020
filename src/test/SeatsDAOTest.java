package test;

import daoClasses.SeatsDAO;
import entity.Seats;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SeatsDAOTest {
    private SeatsDAO _dao;
    private SessionFactory s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory();
        tx = s.getCurrentSession().beginTransaction();
        _dao = new SeatsDAO();
        _dao.setSessionFactory(s);
    }

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    public void getSeats() throws Exception {
        setUp();
        assertTrue(_dao.getByCompId((long) 100).isEmpty());
        List<Seats> _list = _dao.getByCompId((long) 2);
        assertEquals(1, _list.size());
        for (Seats i: _list) {
            assertEquals(2, (long) i.getCompId().getCompId());
            assertEquals(Seats.SeatsType.middle, i.getType());
            assertEquals(2000, (int) i.getNumSeats());
            assertEquals(0, (int) i.getNumFreeSeats());
            assertEquals((double) 2500, i.getPrice());
        }
        shutDown();
    }

    @Test(priority = 2)
    public void findAll() throws Exception {
        setUp();
        List<Seats> _list = _dao.getAll();
        for (Seats i: _list) {
            assertEquals(i.getId(), _dao.getById(i.getId()).getId());
            assertEquals(i.getCompId(), _dao.getById(i.getId()).getCompId());
            assertEquals(i.getNumSeats(), _dao.getById(i.getId()).getNumSeats());
            assertEquals(i.getNumFreeSeats(), _dao.getById(i.getId()).getNumFreeSeats());
            assertEquals(i.getPrice(), _dao.getById(i.getId()).getPrice());
            assertEquals(i.getType(), _dao.getById(i.getId()).getType());
        }
        shutDown();
    }
}
