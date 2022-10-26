package com.example.onlineshop.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class AuthenticationRequest {

    @NotNull(message = "Email can not be null")
    @Email(message = "Invalid email format")
//    @JsonFormat(pattern = "")
       @JsonProperty(value = "username", access = JsonProperty.Access.WRITE_ONLY)
    private String email;
    @NotEmpty(message = "Password can not be empty")
    @NotBlank(message = "Blank password not accepted")
//        @JsonFormat(pattern = "[A-Za-z1-9]")
//    @Pattern(regexp = "^[A-Za-z1-9]")

    private String password;

//    @Min(value = 10, message = "Age can not be less then 10")
//    @Max(value = 100, message = "Age can not be less then 10")
//    private int age;






}
