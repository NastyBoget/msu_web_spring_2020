package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sportsmen", schema = "public", catalog = "sportinfo")
public class Sportsman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sportsman_id")
    private Long sportsmanId;

    @OneToOne
    @JoinColumn(name = "trainer_id", nullable = true)
    private Trainer trainerId;

    @OneToOne
    @JoinColumn(name = "team_id", nullable = true)
    private Team teamId;

    @Basic
    @Column(name = "sportsman_name", nullable = false, length = 50)
    private String sportsmanName;

    @Basic
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    public Sportsman() {}

    public Sportsman(Trainer trainerId, Team teamId,
                     String sportsmanName, Date birthday) {
        this.trainerId = trainerId;
        this.teamId = teamId;
        this.sportsmanName = sportsmanName;
        this.birthday = birthday;
    }

    public void setSportsmanId(Long sportsmanId) {
        this.sportsmanId = sportsmanId;
    }
    public Long getSportsmanId() {
        return sportsmanId;
    }

    public void setTrainerId(Trainer trainerId) {
        this.trainerId = trainerId;
    }

    public Trainer getTrainerId() {
        return trainerId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setSportsmanName(String sportsmanName) {
        this.sportsmanName = sportsmanName;
    }

    public String getSportsmanName() {
        return sportsmanName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sportsman that = (Sportsman) o;
        return Objects.equals(sportsmanId, that.sportsmanId) &&
                Objects.equals(sportsmanName, that.sportsmanName) &&
                Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportsmanId, sportsmanName, birthday);
    }
}
