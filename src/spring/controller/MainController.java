package spring.controller;

import daoClasses.SportsmanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @Autowired
    private SportsmanDAO sportsmanDAO;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(ModelMap map) {
        map.addAttribute("sportsmenList", sportsmanDAO.getAll());
        return "index";
    }
}
