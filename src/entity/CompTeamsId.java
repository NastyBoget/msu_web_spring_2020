package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompTeamsId implements Serializable {
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "comp_id")
    private Long compId;

    public CompTeamsId() {}

    public CompTeamsId(Long compId, Long teamId) {
        this.teamId = teamId;
        this.compId = compId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public Long getCompId() {
        return compId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CompTeamsId that = (CompTeamsId) o;
        return Objects.equals(getTeamId(), that.getTeamId()) &&
                Objects.equals(getCompId(), that.getCompId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeamId(), getCompId());
    }
}
