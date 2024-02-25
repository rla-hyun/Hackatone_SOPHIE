package com.umc.sopi.web.dto;

import lombok.Getter;

import java.time.LocalDate;

public class UserRequestDTO {

    @Getter
    public static class UserDTO {
        private String name;

        private String gender;

        private LocalDate birth;

        private String blood;

        private Long draw;
    }
}
