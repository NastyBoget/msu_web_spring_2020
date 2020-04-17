package forms;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CompetitionForm {
    private Long compId;
    private String compName;
    private String location;
    private String compTime;
    private String status;
    private String sportKind;
    private List<Long> sportsmanList;
    private List<Long> teamList;
    private List<Integer> sportsmenPoints;
    private List<Integer> teamsPoints;
    private List<Integer> sportsmenPlaces;
    private List<Integer> teamsPlaces;
    private List<Short> numSeats;
    private List<Double> prices;

    public CompetitionForm() {
        sportsmanList = new ArrayList<>();
        teamList = new ArrayList<>();
        sportsmenPoints = new ArrayList<>();
        teamsPoints = new ArrayList<>();
        sportsmenPlaces = new ArrayList<>();
        teamsPlaces = new ArrayList<>();
        numSeats = new ArrayList<>();
        prices = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public List<Double> getPrices() {
        return prices;
    }

    public List<Short> getNumSeats() {
        return numSeats;
    }

    public List<Integer> getSportsmenPlaces() {
        return sportsmenPlaces;
    }

    public List<Integer> getSportsmenPoints() {
        return sportsmenPoints;
    }

    public List<Integer> getTeamsPlaces() {
        return teamsPlaces;
    }

    public List<Integer> getTeamsPoints() {
        return teamsPoints;
    }

    public List<Long> getSportsmanList() {
        return sportsmanList;
    }

    public List<Long> getTeamList() {
        return teamList;
    }

    public Long getCompId() {
        return compId;
    }

    public String getSportKind() {
        return sportKind;
    }

    public String getCompName() {
        return compName;
    }

    public String getLocation() {
        return location;
    }

    public String getCompTime() {
        return compTime;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public void setCompTime(String compTime) {
        this.compTime = compTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNumSeats(List<Short> numSeats) {
        this.numSeats = numSeats;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }

    public void setSportKind(String sportKind) {
        this.sportKind = sportKind;
    }

    public void setSportsmanList(List<Long> sportsmanList) {
        this.sportsmanList = sportsmanList;
    }

    public void setSportsmenPlaces(List<Integer> sportsmenPlaces) {
        this.sportsmenPlaces = sportsmenPlaces;
    }

    public void setSportsmenPoints(List<Integer> sportsmenPoints) {
        this.sportsmenPoints = sportsmenPoints;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTeamList(List<Long> teamList) {
        this.teamList = teamList;
    }

    public void setTeamsPlaces(List<Integer> teamsPlaces) {
        this.teamsPlaces = teamsPlaces;
    }

    public void setTeamsPoints(List<Integer> teamsPoints) {
        this.teamsPoints = teamsPoints;
    }
}
