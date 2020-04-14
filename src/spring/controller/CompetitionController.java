package spring.controller;

import daoClasses.CompetitionDAO;
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

    @RequestMapping(value = "/competitions", method = RequestMethod.GET)
    public String getCompetitions(ModelMap map)
    {
        map.addAttribute("competitionsList", competitionDAO.getAll());
        return "competitions";
    }

    @RequestMapping(value = "/competition", method = RequestMethod.GET)
    public String getCompetition(@RequestParam(value="id", required=true) Long id, ModelMap map)
    {
        map.addAttribute("competition", competitionDAO.getById(id));
        return "competition";
    }

}
