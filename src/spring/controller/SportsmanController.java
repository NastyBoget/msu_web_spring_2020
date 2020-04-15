package spring.controller;

import daoClasses.CompSportsmenDAO;
import daoClasses.SportsmanDAO;
import daoClasses.SportsmenTeamsDAO;
import entity.CompSportsmen;
import entity.Sportsman;
import entity.SportsmenTeams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
}