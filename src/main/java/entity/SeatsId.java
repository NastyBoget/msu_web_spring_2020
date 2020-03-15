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
    private Seats.SeatsType seatsType;

    public SeatsId() {}

    public SeatsId(Long compId, Seats.SeatsType seatsType) {
        this.compId = compId;
        this.seatsType = seatsType;
    }

    public Seats.SeatsType getSeatsType() {
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
