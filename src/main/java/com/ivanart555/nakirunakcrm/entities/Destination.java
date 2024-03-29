package com.ivanart555.nakirunakcrm.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "destinations", schema = "public")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Integer id;

    @Column(name = "public_id")
    private Integer publicId;

    @Column(name = "name")
    @Pattern(regexp = "^[А-ЯІ][а-я'ЎўІіА-я][а-я'ЎўІіА-я\\s]{0,48}$", message = "Country name must be 50 letters long max and start with capitalized letter")
    private String name;

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", publicId=" + publicId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return id.equals(that.id) && publicId.equals(that.publicId) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publicId, name);
    }
}
