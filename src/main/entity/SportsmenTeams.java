package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "sportsmen_teams", schema = "public", catalog = "sportinfo")
public class SportsmenTeams implements Serializable {

    @EmbeddedId
    private SportsmenTeamsId id;

    @ManyToOne
    @JoinColumn(name = "sportsman_id", insertable = false, updatable = false)
    private Sportsman sportsmanId;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team teamId;

    public SportsmenTeams() {}

    public SportsmenTeams(Sportsman sportsmanId, Team teamId) {
        this.id = new SportsmenTeamsId(sportsmanId.getSportsmanId(), teamId.getTeamId());
        this.sportsmanId = sportsmanId;
        this.teamId = teamId;
    }

    public SportsmenTeamsId getId() {
        return id;
    }

    public void setId(SportsmenTeamsId id) {
        this.id = id;
    }

    public Sportsman getSportsmanId() {
        return sportsmanId;
    }

    public void setSportsmanId(Sportsman sportsmanId) {
        this.sportsmanId = sportsmanId;
    }

    public Team  getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsmenTeams that = (SportsmenTeams) o;
        return Objects.equals(getSportsmanId(), that.getSportsmanId()) &&
                Objects.equals(getTeamId(), that.getTeamId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSportsmanId(), getTeamId());
    }
}
