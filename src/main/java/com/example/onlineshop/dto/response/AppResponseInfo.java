package com.example.onlineshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppResponseInfo {

    private String message;
    private boolean success;
}
