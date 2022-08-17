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
@Table(name = "Orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Integer id;

    @Column(name = "Timestamp")
    private LocalDateTime timestamp;

    @Column(name = "DestinationID")
    private Integer destinationId;

    @Column(name = "StatusID")
    private Integer statusId;

    @Column(name = "CustomerID")
    private Integer customerId;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "CustomerComment")
    private String customerComment;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", destinationId=" + destinationId +
                ", statusId=" + statusId +
                ", customerId=" + customerId +
                ", comment='" + comment + '\'' +
                ", customerComment='" + customerComment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && timestamp.equals(order.timestamp) && destinationId.equals(order.destinationId) && statusId.equals(order.statusId) && customerId.equals(order.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, destinationId, statusId, customerId);
    }
}
