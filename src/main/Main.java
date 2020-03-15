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
                    comp + " | " +
                    comp.getCompId() + " | " +
                    comp.getCompName() + " | " +
                    comp.getCompStatus() + " | " +
                    comp.getCompTime() + " | " +
                    comp.getLocation() + " | " +
                    comp.getSportKind()
            );
        }
        compSportsmenDAO.setSession(s);
        List<CompSportsmen> compSportsmenList = compSportsmenDAO.getAll();
        for (CompSportsmen comp: compSportsmenList) {
            System.out.println(
                    comp.getCompId() + " | " +
                    comp.getSportsmanId() + " | " +
                    comp.getPlace() + " | " +
                    comp.getPoints()
            );
        }
        compTeamsDAO.setSession(s);
        List<CompTeams> compTeamsList = compTeamsDAO.getAll();
        for (CompTeams comp: compTeamsList) {
            System.out.println(
                    comp.getCompId() + " | " +
                    comp.getTeamId() + " | " +
                    comp.getPlace() + " | " +
                    comp.getPoints()
            );
        }
        seatsDAO.setSession(s);
        List<Seats> seatsList = seatsDAO.getAll();
        for (Seats seats: seatsList) {
            System.out.println(
                    seats.getCompId() + " | " +
                    seats.getNumSeats() + " | " +
                    seats.getNumFreeSeats() + " | " +
                    seats.getType() + " | " +
                    seats.getPrice()
            );
        }
        sportsmanDAO.setSession(s);
        List<Sportsman> sportsmanList = sportsmanDAO.getAll();
        for (Sportsman sportsman: sportsmanList) {
            System.out.println(
                    sportsman + " | " +
                    sportsman.getSportsmanId() + " | " +
                    sportsman.getTrainerId() + " | " +
                    sportsman.getTeamId() + " | " +
                    sportsman.getSportsmanName() + " | " +
                    sportsman.getBirthday()
            );
        }
        sportsmanTeamsDAO.setSession(s);
        List<SportsmenTeams> sportsmenTeamsList = sportsmanTeamsDAO.getAll();
        for (SportsmenTeams sportsmenTeams: sportsmenTeamsList) {
            System.out.println(
                    sportsmenTeams.getSportsmanId() + " | " +
                    sportsmenTeams.getTeamId()
            );
        }
        teamDAO.setSession(s);
        List<Team> teamList = teamDAO.getAll();
        for (Team team: teamList) {
            System.out.println(
                    team + " | " +
                    team.getTeamId() + " | " +
                    team.getTrainerId() + " | " +
                    team.getTeamName()
            );
        }
        trainerDAO.setSession(s);
        List<Trainer> trainerList = trainerDAO.getAll();
        for (Trainer trainer: trainerList) {
            System.out.println(
                    trainer + " | " +
                  trainer.getTrainerId() + " | " +
                  trainer.getName() + " | " +
                  trainer.getBirthday()
            );
        }
        tx.commit();
    }
}