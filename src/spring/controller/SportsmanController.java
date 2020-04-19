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

import java.text.SimpleDateFormat;
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
        try {
            map.addAttribute("sportsmenList", sportsmanDAO.getAll());
            return "sportsmen";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/sportsman", method = RequestMethod.GET)
    public String getSportsman(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        try {
            map.addAttribute("sportsman", sportsmanDAO.getById(id));
            map.addAttribute("teamList", sportsmenTeamsDAO.getBySportsmenId(id));
            map.addAttribute("compList", compSportsmenDAO.getBySportsmanId(id));
            return "sportsman";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/sportsman_delete", method = RequestMethod.GET)
    public String delSportsman(@RequestParam(value="id", required=true) Long id, ModelMap map) {
        try {
            Sportsman sportsman = sportsmanDAO.getById(id);
            List<CompSportsmen> compSportsmen = compSportsmenDAO.getAllBySportsmanId(id);
            for (CompSportsmen item : compSportsmen) {
                compSportsmenDAO.delete(item);
            }
            List<SportsmenTeams> sportsmenTeams = sportsmenTeamsDAO.getAllBySportsmanId(id);
            for (SportsmenTeams item : sportsmenTeams) {
                sportsmenTeamsDAO.delete(item);
            }
            sportsmanDAO.delete(sportsman);
        } catch (Exception ignored) {}
        return "redirect:sportsmen";
    }

    private void saveSportsman(SportsmanForm sportsmanForm) throws Exception {
        Sportsman sportsman = (sportsmanForm.getSportsmanId() == null) ?
                new Sportsman() : sportsmanDAO.getById(sportsmanForm.getSportsmanId());
        sportsman.setSportsmanName(sportsmanForm.getName());
        sportsman.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(sportsmanForm.getBirthday()));
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
            if (sportsmenTeamsDAO.getByCompositeId(sportsman.getSportsmanId(), sportsman.getTeamId().getTeamId()) == null) {
                sportsmenTeamsDAO.save(new SportsmenTeams(sportsman, sportsman.getTeamId()));
            }
        }
    }

    @RequestMapping(value = "/sportsman_add", method = RequestMethod.GET)
    public String addSportsman(ModelMap map) {
        try {
            map.addAttribute("trainers", trainerDAO.getAll());
            map.addAttribute("teams", teamDAO.getAll());
            map.addAttribute("sportsmanForm", new SportsmanForm());
            return "sportsman_update";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/sportsman_add", method = RequestMethod.POST)
    public String addSportsman(ModelMap map,
                               @ModelAttribute("sportsmanForm") SportsmanForm sportsmanForm) {
        try {
            saveSportsman(sportsmanForm);
            return "redirect:sportsmen";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/sportsman_update", method = RequestMethod.GET)
    public String updateSportsman(@RequestParam(value="id", required=true) Long id,
                                  ModelMap map) {
        try {
            map.addAttribute("trainers", trainerDAO.getAll());
            map.addAttribute("teams", teamDAO.getAll());
            SportsmanForm sportsmanForm = new SportsmanForm();
            sportsmanForm.setSportsmanId(id);
            map.addAttribute("sportsmanForm", sportsmanForm);
            return "sportsman_update";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/sportsman_update", method = RequestMethod.POST)
    public String updateSportsman(ModelMap map,
                                  @ModelAttribute("sportsmanForm") SportsmanForm sportsmanForm) {
        try {
            saveSportsman(sportsmanForm);
            return "redirect:sportsman?id=" + sportsmanForm.getSportsmanId().toString();
        } catch (Exception e) {
            return "error";
        }
    }
}