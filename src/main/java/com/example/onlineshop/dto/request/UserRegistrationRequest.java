package com.example.onlineshop.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("User registration model")
public class UserRegistrationRequest {

    @ApiModelProperty(notes = "User first name")
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
