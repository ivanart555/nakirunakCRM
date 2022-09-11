package com.ivanart555.nakirunakcrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers", schema = "public")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    @Column(name = "public_id")
    private Integer publicId;

    @Column(name = "name")
    @Pattern(regexp = "^\\p{L}{2,20}$", message = "Customer name must contain max 20 letters")
    private String name;

    @Column(name = "last_name")
    @Pattern(regexp = "^\\p{L}{0,40}$", message = "Customer last name must contain max 40 letters")
    private String lastName;

    @Column(name = "patronymic")
    @Pattern(regexp = "^\\p{L}{0,30}$", message = "Customer patronymic must contain max 30 letters")
    private String patronymic;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\+375\\s\\(\\d\\d\\)\\s\\d\\d\\d\\-\\d\\d\\-\\d\\d$", message = "Customer phone number " +
            "must meet format requirements. +375 (11) 111-11-11")
    private String phoneNumber;

    @Column(name = "email")
    @Pattern(regexp = "(^$|^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$)", message = "Email must meet format requirements. abc@gmail.com")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public Customer(String name, String lastName, String patronymic, String phoneNumber, String email) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", publicId=" + publicId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && publicId.equals(customer.publicId) && name.equals(customer.name) && Objects.equals(lastName, customer.lastName) && Objects.equals(patronymic, customer.patronymic) && phoneNumber.equals(customer.phoneNumber) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publicId, name, lastName, patronymic, phoneNumber, email);
    }
}
