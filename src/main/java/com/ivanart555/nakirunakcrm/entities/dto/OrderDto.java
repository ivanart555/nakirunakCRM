package com.ivanart555.nakirunakcrm.entities.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private int id;
    private int publicId;
    @NotNull
    private LocalDateTime timestamp;
    private Integer customerId;
    @NotNull
    @NotNull
    private String customerName;
    private String customerLastName;
    private String customerPatronymic;
    @NotNull
    @NotNull
    private String customerPhoneNumber;
    @NotNull
    private String customerEmail;
    @NotNull
    private Integer destinationId;
    private String destinationName;
    private String customerComment;
    private String comment;
    @NotNull
    @NotNull
    private Integer orderStatusId;
    private String orderStatusName;

}
