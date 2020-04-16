package forms;

import java.util.ArrayList;
import java.util.List;

public class TeamForm {
    private Long teamId;
    private String name;
    private Long trainerId;
    private List<Long> sportsmenList;

    public TeamForm() {
        sportsmenList = new ArrayList<>();
        teamId = null;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public List<Long> getSportsmenList() {
        return sportsmenList;
    }

    public void setSportsmenList(List<Long> sportsmenList) {
        this.sportsmenList = sportsmenList;
    }
}
