package com.ivanart555.nakirunakcrm.entities.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private int id;
    @NotNull
    private String customerName;
    private String customerLastName;
    private String customerPatronymic;
    @NotNull
    private String customerPhoneNumber;
    @NotNull
    private String destination;
    private String customerComment;
}
