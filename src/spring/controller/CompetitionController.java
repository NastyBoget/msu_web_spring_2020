package spring.controller;

import daoClasses.*;
import entity.*;
import forms.CompetitionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class CompetitionController {
    @Autowired
    private CompetitionDAO competitionDAO;
    @Autowired
    private CompTeamsDAO compTeamsDAO;
    @Autowired
    private CompSportsmenDAO compSportsmenDAO;
    @Autowired
    private SeatsDAO seatsDAO;
    @Autowired
    private TeamDAO teamDAO;
    @Autowired
    private SportsmanDAO sportsmanDAO;

    @RequestMapping(value = "/competitions", method = RequestMethod.GET)
    public String getCompetitions(ModelMap map) {
        try {
            map.addAttribute("competitionsList", competitionDAO.getAll());
            return "competitions";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/competition", method = RequestMethod.GET)
    public String getCompetition(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        try {
            Competition competition = competitionDAO.getById(id);
            map.addAttribute("competition", competition);
            map.addAttribute("sportsmanList", compSportsmenDAO.getByCompId(id));
            map.addAttribute("teamList", compTeamsDAO.getByCompId(id));
            map.addAttribute("sportsmanResults", compSportsmenDAO.getAllByCompId(id));
            map.addAttribute("teamsResults", compTeamsDAO.getAllByCompId(id));
            return "competition";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/competition_delete", method = RequestMethod.GET)
    public String delCompetition(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        try {
            Competition competition = competitionDAO.getById(id);
            List<CompTeams> compTeams = compTeamsDAO.getAllByCompId(id);
            for (CompTeams item : compTeams) {
                compTeamsDAO.delete(item);
            }
            List<CompSportsmen> compSportsmen = compSportsmenDAO.getAllByCompId(id);
            for (CompSportsmen item : compSportsmen) {
                compSportsmenDAO.delete(item);
            }
            List<Seats> seats = seatsDAO.getByCompId(id);
            for (Seats item : seats) {
                seatsDAO.delete(item);
            }
            competitionDAO.delete(competition);
            return "redirect:competitions";
        } catch (Exception e) {
            return "error";
        }
    }

    private void saveCompetition(CompetitionForm competitionForm) throws Exception {
        Competition competition = (competitionForm.getCompId() == null) ?
                new Competition() : competitionDAO.getById(competitionForm.getCompId());
        competition.setCompName(competitionForm.getCompName());
        switch (competitionForm.getStatus()) {
            case "soon":
                competition.setCompStatus(Competition.CompStatus.soon);
                break;
            case "now":
                competition.setCompStatus(Competition.CompStatus.now);
                break;
            default:
                competition.setCompStatus(Competition.CompStatus.passed);
        }
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(competitionForm.getCompTime());
        competition.setCompTime(new Timestamp(date.getTime()));
        competition.setLocation(competitionForm.getLocation());
        List<Short> numSeats = competitionForm.getNumSeats();
        Integer amountFreeSeats = 0;
        for(Short num: numSeats) {
            amountFreeSeats += num;
        }
        competition.setFreeSeatsStatus(amountFreeSeats != 0);
        switch (competitionForm.getSportKind()) {
            case "hockey":
                competition.setSportKind(Competition.SportKind.hockey);
                break;
            case "gymnastics":
                competition.setSportKind(Competition.SportKind.gymnastics);
                break;
            case "biathlon":
                competition.setSportKind(Competition.SportKind.biathlon);
                break;
            case "synchronized_swimming":
                competition.setSportKind(Competition.SportKind.synchronized_swimming);
                break;
            default:
                competition.setSportKind(Competition.SportKind.football);
        }
        if (competitionForm.getCompId() == null) {
            competitionDAO.save(competition);
        } else {
            competitionDAO.update(competition);
        }
        if (competitionForm.getCompId() == null) {
            List<Long> sportsmanList = competitionForm.getSportsmanList();
            for (Long sportsmanId : sportsmanList) {
                try {
                    compSportsmenDAO.save(new CompSportsmen(competition, sportsmanDAO.getById(sportsmanId)));
                } catch (Exception ignored) {}
            }
        } else {
            List<Sportsman> sportsmanList = compSportsmenDAO.getByCompId(competitionForm.getCompId());
            List<Integer> sPoints = competitionForm.getSportsmenPoints();
            List<Integer> sPlaces = competitionForm.getSportsmenPlaces();
            for(int i = 0; i < sportsmanList.size(); ++i) {
                Sportsman sportsman = sportsmanList.get(i);
                CompSportsmen compSportsmen = compSportsmenDAO.getByCompositeId(competition.getCompId(),
                        sportsman.getSportsmanId());
                compSportsmen.setPlace(sPlaces.get(i));
                compSportsmen.setPoints(sPoints.get(i));
                compSportsmenDAO.update(compSportsmen);
            }
        }
        if (competitionForm.getCompId() == null) {
            List<Long> teamList = competitionForm.getTeamList();
            for (Long teamId : teamList) {
                try {
                    compTeamsDAO.save(new CompTeams(competition, teamDAO.getById(teamId)));
                } catch (Exception ignored) {
                }
            }
        } else {
            List<Team> teamList = compTeamsDAO.getByCompId(competitionForm.getCompId());
            List<Integer> cPoints = competitionForm.getTeamsPoints();
            List<Integer> cPlaces = competitionForm.getTeamsPlaces();
            for(int i = 0; i < teamList.size(); ++i) {
                Team team = teamList.get(i);
                CompTeams compTeams = compTeamsDAO.getByCompositeId(competition.getCompId(),
                        team.getTeamId());
                compTeams.setPlace(cPlaces.get(i));
                compTeams.setPoints(cPoints.get(i));
                compTeamsDAO.update(compTeams);
            }
        }
        if (competitionForm.getCompId() == null) {
            List<Seats.SeatsType> seats = Arrays.asList(Seats.SeatsType.front,
                    Seats.SeatsType.middle, Seats.SeatsType.back);;
            List<Double> prices = competitionForm.getPrices();
            for (int i = 0; i < seats.size(); ++i) {
                try {
                    seatsDAO.save(new Seats(competition, seats.get(i),
                            numSeats.get(i), numSeats.get(i), prices.get(i)));
                } catch (Exception ignored) {}
            }
        }
    }

    @RequestMapping(value = "/competition_add", method = RequestMethod.GET)
    public String addCompetition(ModelMap map) {
        try {
            List<String> seatsTypes = Arrays.asList("front", "middle", "back");
            map.addAttribute("teams", teamDAO.getAll());
            map.addAttribute("sportsmen", sportsmanDAO.getAll());
            map.addAttribute("seats", seatsTypes);
            map.addAttribute("competitionForm", new CompetitionForm());
            return "competition_update";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/competition_add", method = RequestMethod.POST)
    public String addCompetition(ModelMap map,
                                 @ModelAttribute("competitionForm") CompetitionForm competitionForm) {
        try {
            saveCompetition(competitionForm);
            return "redirect:competitions";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/competition_update", method = RequestMethod.GET)
    public String updateCompetition(@RequestParam(value="id", required=true) Long id,
                             ModelMap map) {
        try {
            CompetitionForm competitionForm = new CompetitionForm();
            competitionForm.setCompId(id);
            map.addAttribute("sportsmen", compSportsmenDAO.getByCompId(id));
            map.addAttribute("teams", compTeamsDAO.getByCompId(id));
            map.addAttribute("competitionForm", competitionForm);
            return "competition_update";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/competition_update", method = RequestMethod.POST)
    public String updateCompetition(ModelMap map,
                                    @ModelAttribute("competitionForm") CompetitionForm competitionForm) {
        try {
            saveCompetition(competitionForm);
            return "redirect:competition?id=" + competitionForm.getCompId().toString();
        } catch (Exception e) {
            return "error";
        }
    }
}