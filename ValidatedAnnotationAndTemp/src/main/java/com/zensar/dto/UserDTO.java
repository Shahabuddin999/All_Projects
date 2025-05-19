package com.zensar.dto;

import com.zensar.validationgroup.OnCreate;

import com.zensar.validationgroup.OnUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(groups = OnUpdate.class, message = "ID is required for update")
    private Long id;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Name is required")
    private String name;

    @Email(groups = {OnCreate.class, OnUpdate.class}, message = "Email must be valid")
    private String email;
}
