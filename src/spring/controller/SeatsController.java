package spring.controller;

import daoClasses.CompetitionDAO;
import daoClasses.SeatsDAO;
import entity.Competition;
import entity.Seats;
import forms.BuyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SeatsController {
    @Autowired
    private SeatsDAO seatsDAO;
    @Autowired
    private CompetitionDAO competitionDAO;

    @RequestMapping(value = "/seats", method = RequestMethod.GET)
    public String getCompetitions(@RequestParam(value="id", required=true) Long id,
                                  ModelMap map) {
        try {
            map.addAttribute("seats", seatsDAO.getByCompId(id));
            return "seats";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buyTicket(@RequestParam(value="id", required=true) Long id,
                            @RequestParam(value="type", required=true) Seats.SeatsType type,
                            ModelMap map) {
        try {
            BuyForm form = new BuyForm(id, type);
            map.addAttribute("buyForm", form);
            return "buy";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String updateCompetition(ModelMap map,
                                    @ModelAttribute("buyForm") BuyForm buyForm) {
        try {
            List<Seats> seats = seatsDAO.getByCompId(buyForm.getCompId());
            Integer amountSeats = 0;
            for (Seats seat : seats) {
                if (seat.getType() == buyForm.getSeatsType()) {
                    seat.setNumFreeSeats((short) (seat.getNumFreeSeats() - 1));
                    seatsDAO.update(seat);
                }
                amountSeats += seat.getNumFreeSeats();
            }
            Competition competition = competitionDAO.getById(buyForm.getCompId());
            competition.setFreeSeatsStatus(amountSeats != 0);
            competitionDAO.update(competition);
            return "redirect:competition?id=" + buyForm.getCompId().toString();
        } catch (Exception e) {
            return "error";
        }
    }
}