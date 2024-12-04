package com.example.marketapi.auth.response;

import jakarta.validation.constraints.NotBlank;

public record LoginResponse (@NotBlank String accessToken){
}
