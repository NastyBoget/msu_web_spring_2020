package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/*
    comp_id LongEGER PRIMARY KEY,
    comp_name VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    comp_time TIMESTAMP NOT NULL,
    comp_status STATUS NOT NULL,
    sport_kind SPORT NOT NULL,
    free_seats_status BOOLEAN NOT NULL -- true, if free seats exist
 */

@Entity
@Table(name = "competitions", schema = "public", catalog = "sportinfo")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id")
    private Long compId;

    @Basic
    @Column(name = "comp_name", nullable = false, length = 50)
    private String compName;

    @Basic
    @Column(name = "location", nullable = false, length = 50)
    private String location;

    @Basic
    @Column(name = "comp_time", nullable = false)
    private Timestamp compTime;

    @Basic
    @Column(name = "comp_status", nullable = false)
    private String compStatus;

    @Basic
    @Column(name = "sport_kind", nullable = false)
    private String sportKind;

    @Basic
    @Column(name = "free_seats_status", nullable = false)
    private Boolean freeSeatsStatus;

    public Competition() {}

    public Competition(String compName, String location, Timestamp compTime,
                String compStatus, String sportKind, Boolean freeSeatsStatus) {
        this.compName = compName;
        this.location = location;
        this.compTime = compTime;
        this.compStatus = compStatus;
        this.sportKind = sportKind;
        this.freeSeatsStatus = freeSeatsStatus;
    }
    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompName() {
        return compName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setCompTime(Timestamp compTime) {
        this.compTime = compTime;
    }

    public Timestamp getCompTime() {
        return compTime;
    }

    public void setCompStatus(String compStatus) {
        this.compStatus = compStatus;
    }

    public String getCompStatus() {
        return compStatus;
    }

    public void setSportKind(String sportKind) {
        this.sportKind = sportKind;
    }

    public String getSportKind() {
        return sportKind;
    }

    public void setFreeSeatsStatus(Boolean freeSeatsStatus) {
        this.freeSeatsStatus = freeSeatsStatus;
    }

    public Boolean isFreeSeatsStatus() {
        return freeSeatsStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competition that = (Competition) o;
        return Objects.equals(compId, that.compId) &&
                Objects.equals(compName, that.compName) &&
                Objects.equals(location, that.location) &&
                Objects.equals(compTime, that.compTime) &&
                Objects.equals(compStatus, that.compStatus) &&
                Objects.equals(sportKind, that.sportKind) &&
                Objects.equals(freeSeatsStatus, that.freeSeatsStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compId, compName, location, compTime,
                compStatus, sportKind, freeSeatsStatus);
    }
}