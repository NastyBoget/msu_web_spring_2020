import daoClasses.TrainerDAO;
import entity.Trainer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;

public class TrainerDAOTest {
    private TrainerDAO _dao;
    private Session s;
    private Transaction tx;

    public void setUp() throws Exception {
        s = new Configuration().configure().buildSessionFactory().openSession();
        tx = s.beginTransaction();
        _dao = new TrainerDAO();
        _dao.setSession(s);
    }

    public void shutDown() throws Exception {
        tx.commit();
        s.close();
    }

    @Test(priority = 0)
    public void getTrainer() throws Exception {
        setUp();
        assertNull(_dao.getById((long) -1));

        Trainer trainer = _dao.getById((long) 1);
        assertEquals(trainer.getName(), "Tyler Bertuzzi");
        assertEquals(trainer.getBirthday().toString(), "1990-12-15 00:00:00.0");

        shutDown();
    }

    @Test(priority = 1)
    public void saveUpdateDelete() throws Exception {
        setUp();
        Trainer tmp = new Trainer();
        tmp.setName("Roope Hinz");
        tmp.setBirthday(new Timestamp(1979, 5, 13, 0, 0, 0,0));
        _dao.save(tmp);
        assertEquals("Roope Hinz", _dao.getById(tmp.getTrainerId()).getName());
        assertEquals(new Timestamp(1979, 5, 13, 0, 0, 0,0),
                _dao.getById(tmp.getTrainerId()).getBirthday());

        tmp.setName("Tyler Bertuzzi");
        _dao.update(tmp);
        assertEquals("Tyler Bertuzzi", _dao.getById(tmp.getTrainerId()).getName());
        tmp.setBirthday(new Timestamp(1990, 12, 15, 0, 0, 0, 0));
        _dao.update(tmp);
        assertEquals(new Timestamp(1990, 12, 15, 0, 0, 0, 0),
                _dao.getById(tmp.getTrainerId()).getBirthday());

        _dao.delete(tmp);
        assertNull(_dao.getById(tmp.getTrainerId()));

        shutDown();
    }

    @Test(priority = 2)
    public void findAll() throws Exception {
        setUp();
        List<Trainer> _list = _dao.getAll();
        for (Trainer i: _list) {
            assertEquals(i.getName(), _dao.getById(i.getTrainerId()).getName());
            assertEquals(i.getBirthday(), _dao.getById(i.getTrainerId()).getBirthday());
        }
        shutDown();
    }
}
