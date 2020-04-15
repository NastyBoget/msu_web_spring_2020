package spring.controller;

import daoClasses.CompSportsmenDAO;
import daoClasses.CompTeamsDAO;
import daoClasses.CompetitionDAO;
import daoClasses.SeatsDAO;
import entity.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompetitionController {
    @Autowired
    private CompetitionDAO competitionDAO;
    @Autowired
    private CompTeamsDAO compTeamsDAO;
    @Autowired
    private CompSportsmenDAO compSportsmenDAO;

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
        return "competition";
    }

}