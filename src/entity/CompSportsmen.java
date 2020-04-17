package entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "comp_sportsmen", schema = "public", catalog = "sportinfo")
public class CompSportsmen implements Serializable {

    @Basic
    @Column(name = "place", nullable = false)
    private Integer place;

    @Basic
    @Column(name = "points", nullable = false)
    private Integer points;

    @EmbeddedId
    private CompSportsmenId id;

    @ManyToOne
    @JoinColumn(name = "sportsman_id", insertable = false, updatable = false)
    private Sportsman sportsmanId;

    @ManyToOne
    @JoinColumn(name = "comp_id", insertable = false, updatable = false)
    private Competition compId;

    public CompSportsmen() {}

    public CompSportsmen(@NotNull Competition compId, @NotNull Sportsman sportsmanId) {
        this.id = new CompSportsmenId(compId.getCompId(), sportsmanId.getSportsmanId());
        this.sportsmanId = sportsmanId;
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

    public CompSportsmenId getId() {
        return id;
    }

    public void setId(CompSportsmenId id) {
        this.id = id;
    }

    public Sportsman getSportsmanId() {
        return sportsmanId;
    }

    public void setSportsmanId(Sportsman sportsmanId) {
        this.sportsmanId = sportsmanId;
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
        CompSportsmen that = (CompSportsmen) o;
        return Objects.equals(getSportsmanId(), that.getSportsmanId()) &&
                Objects.equals(getCompId(), that.getCompId()) &&
                Objects.equals(place, that.place) &&
                Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSportsmanId(), getCompId(), place, points);
    }
}
