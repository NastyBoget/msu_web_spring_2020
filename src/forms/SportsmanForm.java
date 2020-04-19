package forms;

public class SportsmanForm {
    private Long sportsmanId;
    private String birthday;
    private Long trainerId;
    private String name;
    private Long teamId;

    public SportsmanForm() {
        sportsmanId = null;
    }

    public Long getSportsmanId() {
        return sportsmanId;
    }

    public void setSportsmanId(Long id) {
        sportsmanId = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
