package controller;

import daoClasses.SportsmanDAO;
import entity.Sportsman;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping(value = "/")
public class SportsmanController {
    private static final SportsmanDAO _dao = new SportsmanDAO();

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String getIndex(Model model) {
//        return "index";
//    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getSportsmen(Model model) {
        try {
            List<Sportsman> sportsmen = _dao.getAll();
            model.addAttribute("sportsmen", sportsmen);
        } catch (HibernateException e) {
            return "error";
        }
        return "sportsmen";
    }

    @RequestMapping(value = "/sportsman", method = RequestMethod.GET)
    public String getSportsman(@RequestParam(value="id", required=true) Long id,
                               Model model) {
        try {
            Sportsman sportsman = _dao.getById(id);
            model.addAttribute("sportsman", sportsman);
        } catch (HibernateException e) {
            return "error";
        }
        return "sportsman";
    }
}
