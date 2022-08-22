package com.ivanart555.nakirunakcrm.entities.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private int id;
    @NotNull
    private String name;
    private String lastName;
    private String patronymic;
    @NotNull
    private String phoneNumber;
    private String email;
}
