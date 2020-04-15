package spring.controller;

import daoClasses.CompSportsmenDAO;
import daoClasses.CompTeamsDAO;
import daoClasses.CompetitionDAO;
import daoClasses.SeatsDAO;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/competitions", method = RequestMethod.GET)
    public String getCompetitions(ModelMap map) {
        map.addAttribute("competitionsList", competitionDAO.getAll());
        return "competitions";
    }

    @RequestMapping(value = "/competition", method = RequestMethod.GET)
    public String getCompetition(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        Competition competition = competitionDAO.getById(id);
        map.addAttribute("competition", competition);
        map.addAttribute("sportsmanList", compSportsmenDAO.getByCompId(id));
        map.addAttribute("teamList", compTeamsDAO.getByCompId(id));
        map.addAttribute("sportsmanResults", compSportsmenDAO.getAllByCompId(id));
        map.addAttribute("teamsResults", compTeamsDAO.getAllByCompId(id));
        return "competition";
    }

    @RequestMapping(value = "/competition_delete", method = RequestMethod.GET)
    public String delCompetition(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        Competition competition = competitionDAO.getById(id);
        List<CompTeams> compTeams = compTeamsDAO.getAllByCompId(id);
        for(CompTeams item: compTeams) {
            compTeamsDAO.delete(item);
        }
        List<CompSportsmen> compSportsmen = compSportsmenDAO.getAllByCompId(id);
        for(CompSportsmen item: compSportsmen) {
            compSportsmenDAO.delete(item);
        }
        List<Seats> seats = seatsDAO.getByCompId(id);
        for(Seats item: seats) {
            seatsDAO.delete(item);
        }
        competitionDAO.delete(competition);
        return "redirect:competitions";
    }
}