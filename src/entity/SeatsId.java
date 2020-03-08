package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SeatsId implements Serializable {

    @Column(name = "comp_id")
    private Long compId;

    @Column(name = "seats_type")
    private String seatsType;

    public SeatsId() {}

    public SeatsId(Long compId, String seatsType) {
        this.compId = compId;
        this.seatsType = seatsType;
    }

    public String getSeatsType() {
        return seatsType;
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
        SeatsId that = (SeatsId) o;
        return Objects.equals(getSeatsType(), that.getSeatsType()) &&
                Objects.equals(getCompId(), that.getCompId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeatsType(), getCompId());
    }
}
