package spring.controller;

import daoClasses.*;
import entity.*;
import forms.TeamForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    @Autowired
    private TrainerDAO trainerDAO;

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public String getTeams(ModelMap map) {
        try {
            map.addAttribute("teamsList", teamDAO.getAll());
            return "teams";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public String getTeam(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        try {
            map.addAttribute("team", teamDAO.getById(id));
            map.addAttribute("compList", compTeamsDAO.getByTeamId(id));
            map.addAttribute("sportsmanList", sportsmenTeamsDAO.getByTeamId(id));
            return "team";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/team_delete", method = RequestMethod.GET)
    public String delTeam(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        try {
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
        } catch (Exception e) {
            return "error";
        }
    }

    private void saveTeam(TeamForm teamForm) {
        Team team = (teamForm.getTeamId() == null) ?
                new Team() : teamDAO.getById(teamForm.getTeamId());
        team.setTeamName(teamForm.getName());
        team.setTrainerId(trainerDAO.getById(teamForm.getTrainerId()));
        if (teamForm.getTeamId() == null) {
            teamDAO.save(team);
        } else {
            teamDAO.update(team);
        }
        List<Sportsman> sportsmen = new ArrayList<Sportsman>();
        for(Long id: teamForm.getSportsmenList()) {
            sportsmen.add(sportsmanDAO.getById(id));
        }
        for(Sportsman sportsman: sportsmen) {
            try {
                sportsmenTeamsDAO.save(new SportsmenTeams(sportsman, team));
            } catch(Exception ignored) {}
        }
    }

    @RequestMapping(value = "/team_add", method = RequestMethod.GET)
    public String addTeam(ModelMap map) {
        try {
            map.addAttribute("trainers", trainerDAO.getAll());
            map.addAttribute("sportsmen", sportsmanDAO.getAll());
            map.addAttribute("teamForm", new TeamForm());
            return "team_update";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/team_add", method = RequestMethod.POST)
    public String addTeam(ModelMap map,
                          @ModelAttribute("teamForm") TeamForm teamForm) {
        try {
            saveTeam(teamForm);
            return "redirect:teams";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/team_update", method = RequestMethod.GET)
    public String updateTeam(@RequestParam(value="id", required=true) Long id,
                             ModelMap map) {
        try {
            map.addAttribute("trainers", trainerDAO.getAll());
            map.addAttribute("sportsmen", sportsmanDAO.getAll());
            TeamForm teamForm = new TeamForm();
            teamForm.setTeamId(id);
            map.addAttribute("teamForm", teamForm);
            return "team_update";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/team_update", method = RequestMethod.POST)
    public String updateTeam(ModelMap map,
                             @ModelAttribute("teamForm") TeamForm teamForm) {
        try {
            saveTeam(teamForm);
            return "redirect:team?id=" + teamForm.getTeamId().toString();
        } catch (Exception e) {
            return "error";
        }
    }
}
