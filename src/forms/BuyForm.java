package forms;

public class BuyForm {
    private Long compId;
    private String seatsType;
    private String email;
    private String card;

    public BuyForm() {}

    public BuyForm(Long id, String type) {
        compId = id;
        seatsType = type;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Long getCompId() {
        return compId;
    }

    public String getSeatsType() {
        return seatsType;
    }

    public void setSeatsType(String seatsType) {
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
