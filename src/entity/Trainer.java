package entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Date;

/*
    trainer_id LongEGER PRIMARY KEY,
    trainer_name VARCHAR(50) NOT NULL,
    birthday DATE NOT NULL
 */

@Entity
@Table(name = "trainers", schema = "public", catalog = "sportinfo")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Long trainerId;

    @Basic
    @Column(name = "trainer_name", nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    public Trainer() {}
    
    public Trainer(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }
    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer that = (Trainer) o;
        return Objects.equals(trainerId, that.trainerId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerId, name, birthday);
    }
}
