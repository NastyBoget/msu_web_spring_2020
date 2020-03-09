//import org.hibernate.*;
//
//import org.hibernate.query.Query;
//import org.hibernate.cfg.Configuration;
//
//import javax.persistence.metamodel.EntityType;
//
//public class Main {
//    private static final SessionFactory ourSessionFactory;
//
//    static {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//
//            ourSessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }
//
//    public static void main(final String[] args) throws Exception {
//        final Session session = getSession();
//        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//        }
//
//    }
//}

import daoClasses.*;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session s = new Configuration().configure().buildSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        CompetitionDAO competitionDAO = new CompetitionDAO();
        CompSportsmenDAO compSportsmenDAO = new CompSportsmenDAO();
        CompTeamsDAO compTeamsDAO = new CompTeamsDAO();
        SeatsDAO seatsDAO = new SeatsDAO();
        SportsmanDAO sportsmanDAO = new SportsmanDAO();
        SportsmenTeamsDAO sportsmanTeamsDAO = new SportsmenTeamsDAO();
        TeamDAO teamDAO = new TeamDAO();
        TrainerDAO trainerDAO = new TrainerDAO();
        competitionDAO.setSession(s);
        List<Competition> compList = competitionDAO.getAll();
        for (Competition comp: compList) {
            System.out.println(
                    comp.getCompId() + " | " +
                    comp.getCompName() + " | " +
                    comp.getCompStatus() + " | " +
                    comp.getCompTime() + " | " +
                    comp.getLocation() + " | " +
                    comp.getSportKind()
            );
        }
        tx.commit();
    }
}