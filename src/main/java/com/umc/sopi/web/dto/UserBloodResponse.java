package com.umc.sopi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class UserBloodResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterUserBloodResultDTO{
        String type;
        Long number;
        LocalDate date;
        String location;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserBloodResultDTO{
        String type;
        Long number;
        LocalDate date;
        String location;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserBloodListDTO{
        List<UserBloodResultDTO> userBloodList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DayDTO {
        Long LeftDay;
    }
}
