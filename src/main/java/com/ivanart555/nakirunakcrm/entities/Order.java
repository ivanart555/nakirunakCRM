package com.ivanart555.nakirunakcrm.entities;

import jakarta.persistence.*;
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

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "comment")
    private String comment;

    @Column(name = "customer_comment")
    private String customerComment;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
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
        return id.equals(order.id) && timestamp.equals(order.timestamp) && destination.equals(order.destination) && orderStatus.equals(order.orderStatus) && customer.equals(order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, destination, orderStatus, customer);
    }
}
