package test;

import daoClasses.CompSportsmenDAO;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CompSportsmenDAOTest {
    private CompSportsmenDAO _dao;
    private Session s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory().openSession();
        tx = s.beginTransaction();
        _dao = new CompSportsmenDAO();
        _dao.setSession(s);
    }

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    void getCompSportsmen() throws Exception {
        setUp();

        shutDown();
    }

    @Test(priority = 1)
    void saveUpdateDelete() throws Exception {
        setUp();

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