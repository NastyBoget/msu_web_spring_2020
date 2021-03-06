package entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/*
    comp_id INTEGER REFERENCES competitions NOT NULL,
    seats_type SEATS NOT NULL,
    num_seats SMALLINT NOT NULL CHECK (num_seats > 0),
    num_free_seats SMALLINT NOT NULL CHECK (num_free_seats >= 0),
    price NUMERIC NOT NULL,
 */

@Entity
@Table(name = "seats_info", schema = "public", catalog = "sportinfo")
@TypeDef(name = "pgsql_enum", typeClass = PSQLType.class)
public class Seats implements Serializable {
    public enum SeatsType {
        front,
        middle,
        back
    }

    @EmbeddedId
    private SeatsId id;

    @ManyToOne
    @JoinColumn(name = "comp_id", nullable = false, insertable = false, updatable = false)
    private Competition compId;

    @Enumerated(EnumType.STRING)
    @Column(name = "seats_type", nullable = false, insertable = false, updatable = false)
    @Type(type = "pgsql_enum")
    private SeatsType type;

    @Basic
    @Column(name = "num_seats", nullable = false)
    private Short numSeats;

    @Basic
    @Column(name = "num_free_seats", nullable = false)
    private Short numFreeSeats;

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private Double price;

    public Seats() {}

    public Seats(@NotNull Competition compId, SeatsType type,
                 Short numSeats, Short numFreeSeats, Double price) {
        this.id = new SeatsId(compId.getCompId(), type);
        this.compId = compId;
        this.type = type;
        this.numSeats = numSeats;
        this.numFreeSeats = numFreeSeats;
        this.price = price;
    }

    public SeatsId getId() {
        return id;
    }

    public void setId(SeatsId id) {
        this.id = id;
    }

    public void setCompId(Competition compId) {
        this.compId = compId;
    }

    public Competition getCompId() {
        return compId;
    }

    public void setType(SeatsType type) {
        this.type = type;
    }

    public SeatsType getType() {
        return type;
    }

    public void setNumSeats(Short numSeats) {
        this.numSeats = numSeats;
    }

    public Short getNumSeats() {
        return numSeats;
    }

    public void setNumFreeSeats(Short numFreeSeats) {
        this.numFreeSeats = numFreeSeats;
    }

    public Short getNumFreeSeats() {
        return numFreeSeats;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seats that = (Seats) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(numSeats, that.numSeats) &&
                Objects.equals(numFreeSeats, that.numFreeSeats) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, numSeats, numFreeSeats, price);
    }
}
