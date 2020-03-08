package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SportsmenTeamsId implements Serializable {
    @Column(name = "sportsman_id")
    private Long sportsmanId;

    @Column(name = "comp_id")
    private Long teamId;

    public SportsmenTeamsId() {}

    public SportsmenTeamsId(Long sportsmanId, Long teamId) {
        this.sportsmanId = sportsmanId;
        this.teamId = teamId;
    }

    public Long getSportsmanId() {
        return sportsmanId;
    }

    public Long getTeamId() {
        return teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SportsmenTeamsId that = (SportsmenTeamsId) o;
        return Objects.equals(getSportsmanId(), that.getSportsmanId()) &&
                Objects.equals(getTeamId(), that.getTeamId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSportsmanId(), getTeamId());
    }
}
