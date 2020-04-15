package spring.controller;

import daoClasses.SeatsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SeatsController {
    @Autowired
    private SeatsDAO seatsDAO;

    @RequestMapping(value = "/seats", method = RequestMethod.GET)
    public String getCompetitions(@RequestParam(value="id", required=true) Long id,
                                  ModelMap map) {
        map.addAttribute("seats", seatsDAO.getByCompId(id));
        return "seats";
    }
}