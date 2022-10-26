package com.example.onlineshop.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppResponseInfo {

    @JsonProperty(value = "appMessage", access = JsonProperty.Access.READ_ONLY)
    private String message;
    private boolean success;
}
