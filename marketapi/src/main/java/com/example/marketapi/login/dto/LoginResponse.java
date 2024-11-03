package com.example.marketapi.login.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginResponse (@NotBlank String accessToken){
}
