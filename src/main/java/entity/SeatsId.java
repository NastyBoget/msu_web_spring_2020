package entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@TypeDef(name = "pgsql_enum", typeClass = PSQLType.class)
public class SeatsId implements Serializable {

    @Column(name = "comp_id")
    private Long compId;

    @Enumerated(EnumType.STRING)
    @Column(name = "seats_type")
    @Type(type = "pgsql_enum")
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
