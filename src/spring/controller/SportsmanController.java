package spring.controller;

import daoClasses.SportsmanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SportsmanController {
    @Autowired
    private SportsmanDAO sportsmanDAO;

    @RequestMapping(value = "/sportsmen", method = RequestMethod.GET)
    public String getSportsmen(ModelMap map)
    {
        map.addAttribute("sportsmenList", sportsmanDAO.getAll());
        return "sportsmen";
    }

    @RequestMapping(value = "/sportsman", method = RequestMethod.GET)
    public String getSportsman(@RequestParam(value="id", required=true) Long id, ModelMap map)
    {
        map.addAttribute("sportsman", sportsmanDAO.getById(id));
        return "sportsman";
    }

}