package com.umc.sopi.web.dto;

import lombok.Getter;

import java.time.LocalDate;

public class UserBloodRequest {
    @Getter
    public static class RegisterDTO{
        String type;
        Long number;
        LocalDate date;
        String location;
    }
}
