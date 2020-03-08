package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompSportsmenId implements Serializable {
    @Column(name = "sportsman_id")
    private Long sportsmenId;

    @Column(name = "comp_id")
    private Long compId;

    public CompSportsmenId() {}

    public CompSportsmenId(Long compId, Long sportsmenId) {
        this.sportsmenId = sportsmenId;
        this.compId = compId;
    }

    public Long getSportsmenId() {
        return sportsmenId;
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
        CompSportsmenId that = (CompSportsmenId) o;
        return Objects.equals(getSportsmenId(), that.getSportsmenId()) &&
                Objects.equals(getCompId(), that.getCompId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSportsmenId(), getCompId());
    }
}
