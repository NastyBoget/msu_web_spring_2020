package forms;

import entity.Seats;

public class BuyForm {
    private Long compId;
    private Seats.SeatsType seatsType;
    private String email;
    private String card;

    public BuyForm(Long id, Seats.SeatsType type) {
        compId = id;
        seatsType = type;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Long getCompId() {
        return compId;
    }

    public Seats.SeatsType getSeatsType() {
        return seatsType;
    }

    public void setSeatsType(Seats.SeatsType seatsType) {
        this.seatsType = seatsType;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
