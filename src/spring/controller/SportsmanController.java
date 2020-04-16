package spring.controller;

import daoClasses.*;
import entity.CompSportsmen;
import entity.Sportsman;
import entity.SportsmenTeams;
import forms.SportsmanForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SportsmanController {
    @Autowired
    private SportsmanDAO sportsmanDAO;
    @Autowired
    private CompSportsmenDAO compSportsmenDAO;
    @Autowired
    private SportsmenTeamsDAO sportsmenTeamsDAO;
    @Autowired
    private TrainerDAO trainerDAO;
    @Autowired
    private TeamDAO teamDAO;

    @RequestMapping(value = "/sportsmen", method = RequestMethod.GET)
    public String getSportsmen(ModelMap map) {
        map.addAttribute("sportsmenList", sportsmanDAO.getAll());
        return "sportsmen";
    }

    @RequestMapping(value = "/sportsman", method = RequestMethod.GET)
    public String getSportsman(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        map.addAttribute("sportsman", sportsmanDAO.getById(id));
        map.addAttribute("teamList", sportsmenTeamsDAO.getBySportsmenId(id));
        map.addAttribute("compList", compSportsmenDAO.getBySportsmanId(id));
        return "sportsman";
    }

    @RequestMapping(value = "/sportsman_delete", method = RequestMethod.GET)
    public String delSportsman(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        Sportsman sportsman = sportsmanDAO.getById(id);
        List<CompSportsmen> compSportsmen = compSportsmenDAO.getAllBySportsmanId(id);
        for(CompSportsmen item: compSportsmen) {
            compSportsmenDAO.delete(item);
        }
        List<SportsmenTeams> sportsmenTeams = sportsmenTeamsDAO.getAllBySportsmanId(id);
        for(SportsmenTeams item: sportsmenTeams) {
            sportsmenTeamsDAO.delete(item);
        }
        sportsmanDAO.delete(sportsman);
        return "redirect:sportsmen";
    }

    private void saveSportsman(SportsmanForm sportsmanForm) {
        Sportsman sportsman = (sportsmanForm.getSportsmanId() == null) ?
                new Sportsman() : sportsmanDAO.getById(sportsmanForm.getSportsmanId());
        sportsman.setSportsmanName(sportsmanForm.getName());
        sportsman.setBirthday(sportsmanForm.getBirthday());
        if (sportsmanForm.getTeamId() == null) {
            sportsman.setTeamId(null);
        } else {
            sportsman.setTeamId(teamDAO.getById(sportsmanForm.getTeamId()));
        }
        if (sportsmanForm.getTrainerId() == null) {
            sportsman.setTrainerId(null);
        } else {
            sportsman.setTrainerId(trainerDAO.getById(sportsmanForm.getTrainerId()));
        }
        if (sportsmanForm.getSportsmanId() == null) {
            sportsmanDAO.save(sportsman);
        } else {
            sportsmanDAO.update(sportsman);
        }
        if (sportsman.getTeamId() != null) {
            sportsmenTeamsDAO.save(new SportsmenTeams(sportsman, sportsman.getTeamId()));
        }
    }

    @RequestMapping(value = "/sportsman_add", method = RequestMethod.GET)
    public String addSportsman(ModelMap map) {
        map.addAttribute("trainers", trainerDAO.getAll());
        map.addAttribute("teams", teamDAO.getAll());
        map.addAttribute("sportsmanForm", new SportsmanForm());
        return "sportsman_update";
    }

    @RequestMapping(value = "/sportsman_add", method = RequestMethod.POST)
    public String addSportsman(ModelMap map,
                               @ModelAttribute("sportsmanForm") SportsmanForm sportsmanForm) {
        saveSportsman(sportsmanForm);
        return "redirect:sportsmen";
    }

    @RequestMapping(value = "/sportsman_update", method = RequestMethod.GET)
    public String updateSportsman(@RequestParam(value="id", required=true) Long id,
                                  ModelMap map) {
        map.addAttribute("trainers", trainerDAO.getAll());
        map.addAttribute("teams", teamDAO.getAll());
        SportsmanForm sportsmanForm = new SportsmanForm();
        sportsmanForm.setSportsmanId(id);
        map.addAttribute("sportsmanForm", sportsmanForm);
        return "sportsman_update";
    }

    @RequestMapping(value = "/sportsman_update", method = RequestMethod.POST)
    public String updateSportsman(ModelMap map,
                                  @ModelAttribute("sportsmanForm") SportsmanForm sportsmanForm) {
        saveSportsman(sportsmanForm);
        return "redirect:sportsman?id=" + sportsmanForm.getSportsmanId().toString();
    }
}