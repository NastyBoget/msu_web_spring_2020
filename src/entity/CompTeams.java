package entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "comp_teams", schema = "public", catalog = "sportinfo")
public class CompTeams implements Serializable {

    @Basic
    @Column(name = "place", nullable = false)
    private Integer place;

    @Basic
    @Column(name = "points", nullable = false)
    private Integer points;

    @EmbeddedId
    private CompTeamsId id;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team teamId;

    @ManyToOne
    @JoinColumn(name = "comp_id", insertable = false, updatable = false)
    private Competition compId;

    public CompTeams() {}

    public CompTeams(@NotNull Competition compId, @NotNull Team teamId) {
        this.id = new CompTeamsId(compId.getCompId(), teamId.getTeamId());
        this.teamId = teamId;
        this.compId = compId;
        this.place = null;
        this.points = null;
    }

    public Integer getPlace() {
        return place;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public CompTeamsId getId() {
        return id;
    }

    public void setId(CompTeamsId id) {
        this.id = id;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    public Competition getCompId() {
        return compId;
    }

    public void setCompId(Competition compId) {
        this.compId = compId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompTeams that = (CompTeams) o;
        return Objects.equals(getTeamId(), that.getTeamId()) &&
                Objects.equals(getCompId(), that.getCompId()) &&
                Objects.equals(place, that.place) &&
                Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeamId(), getCompId(), place, points);
    }
}
