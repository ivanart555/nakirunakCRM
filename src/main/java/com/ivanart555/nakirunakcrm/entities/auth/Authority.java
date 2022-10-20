package com.ivanart555.nakirunakcrm.entities.auth;

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
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(name = "name")
    @Pattern(regexp = "^\\p{L}{2,50}$", message = "Authority name must contain max 50 letters")
    private String name;

    @Override
    public String toString() {
        return "Authority{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return name.equals(authority.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}