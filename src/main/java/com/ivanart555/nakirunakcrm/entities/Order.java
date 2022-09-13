package com.ivanart555.nakirunakcrm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "public_id")
    @NotNull
    private Integer publicId;

    @Column(name = "timestamp")
    @NotNull
    private LocalDateTime timestamp;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "destination_id")
    @NotNull
    private Destination destination;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "order_status_id")
    @NotNull
    private OrderStatus orderStatus;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Customer customer;

    @Column(name = "comment")
    @Pattern(regexp = "^.{0,1000}$", flags = Pattern.Flag.DOTALL, message = "Comment must contain max 1000 symbols")
    private String comment;

    @Column(name = "customer_comment")
    @Pattern(regexp = "^.{0,1000}$", flags = Pattern.Flag.DOTALL, message = "Customer comment must contain max 1000 symbols")
    private String customerComment;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", public_id=" + publicId +
                ", timestamp=" + timestamp +
                ", destination=" + destination +
                ", orderStatus=" + orderStatus +
                ", customer=" + customer +
                ", comment='" + comment + '\'' +
                ", customerComment='" + customerComment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && publicId.equals(order.publicId) && timestamp.equals(order.timestamp) && destination.equals(order.destination) && orderStatus.equals(order.orderStatus) && customer.equals(order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publicId, timestamp, destination, orderStatus, customer);
    }
}
