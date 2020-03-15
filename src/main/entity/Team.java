package entity;

import javax.persistence.*;
import java.util.Objects;

/*
    team_id LongEGER PRIMARY KEY,
    trainer_id LongEGER REFERENCES trainers NOT NULL,
    team_name VARCHAR(50) NOT NULL
 */

@Entity
@Table(name = "teams", schema = "public", catalog = "sportinfo")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @OneToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainerId;

    @Basic
    @Column(name = "team_name", nullable = false, length = 50)
    private String teamName;

    public Team() {}

    public Team(Trainer trainerId, String teamName) {
        this.trainerId = trainerId;
        this.teamName = teamName;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTrainerId(Trainer trainerId) {
        this.trainerId = trainerId;
    }

    public Trainer getTrainerId() {
        return trainerId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team that = (Team) o;
        return Objects.equals(teamId, that.teamId) &&
                Objects.equals(teamName, that.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, teamName);
    }
}
