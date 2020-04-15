package spring.controller;

import daoClasses.CompTeamsDAO;
import daoClasses.SportsmenTeamsDAO;
import daoClasses.TeamDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamController {
    @Autowired
    private TeamDAO teamDAO;
    @Autowired
    private CompTeamsDAO compTeamsDAO;
    @Autowired
    private SportsmenTeamsDAO sportsmenTeamsDAO;

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public String getTeams(ModelMap map) {
        map.addAttribute("teamsList", teamDAO.getAll());
        return "teams";
    }

    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public String getTeam(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        map.addAttribute("team", teamDAO.getById(id));
        map.addAttribute("compList", compTeamsDAO.getByTeamId(id));
        map.addAttribute("sportsmanList", sportsmenTeamsDAO.getByTeamId(id));
        return "team";
    }

}
