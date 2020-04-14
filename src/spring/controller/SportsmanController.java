package spring.controller;

import daoClasses.SportsmanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SportsmanController {
    @Autowired
    private SportsmanDAO sportsmanDAO;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSportsmen(ModelMap map)
    {
        map.addAttribute("sportsmen", sportsmanDAO.getAll());
        return "index";
    }
}