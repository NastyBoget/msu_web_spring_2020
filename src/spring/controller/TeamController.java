package spring.controller;

import daoClasses.CompTeamsDAO;
import daoClasses.SportsmanDAO;
import daoClasses.SportsmenTeamsDAO;
import daoClasses.TeamDAO;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeamController {
    @Autowired
    private TeamDAO teamDAO;
    @Autowired
    private CompTeamsDAO compTeamsDAO;
    @Autowired
    private SportsmenTeamsDAO sportsmenTeamsDAO;
    @Autowired
    private SportsmanDAO sportsmanDAO;

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

    @RequestMapping(value = "/team_delete", method = RequestMethod.GET)
    public String delTeam(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        Team team = teamDAO.getById(id);
        List<CompTeams> compTeams = compTeamsDAO.getAllByTeamId(id);
        for(CompTeams item: compTeams) {
            compTeamsDAO.delete(item);
        }
        List<Sportsman> sportsmen = sportsmanDAO.getByTeamId(id);
        for(Sportsman sportsman: sportsmen) {
            sportsman.setTeamId(null);
            sportsmanDAO.update(sportsman);
        }
        List<SportsmenTeams> sportsmenTeams = sportsmenTeamsDAO.getAllByTeamId(id);
        for(SportsmenTeams item: sportsmenTeams) {
            sportsmenTeamsDAO.delete(item);
        }
        teamDAO.delete(team);
        return "redirect:teams";
    }
}
