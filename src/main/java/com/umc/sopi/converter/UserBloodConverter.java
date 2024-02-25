package com.umc.sopi.converter;

import com.umc.sopi.domain.UserBlood;
import com.umc.sopi.web.dto.UserBloodRequest;
import com.umc.sopi.web.dto.UserBloodResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserBloodConverter {
    public static UserBlood toUserBlood(UserBloodRequest.RegisterDTO request) {
        return UserBlood.builder()
                .number(request.getNumber())
                .location(request.getLocation())
                .date(request.getDate())
                .type(request.getType())
                .build();
    }

    public static UserBloodResponse.RegisterUserBloodResultDTO toRegisterUserBloodResultDTO(UserBlood userBlood) {
        return UserBloodResponse.RegisterUserBloodResultDTO.builder()
                .date(userBlood.getDate())
                .location(userBlood.getLocation())
                .number(userBlood.getNumber())
                .type(userBlood.getType())
                .build();
    }

    public static UserBloodResponse.UserBloodResultDTO userBloodResultDTO(UserBlood userBlood) {
        return UserBloodResponse.UserBloodResultDTO.builder()
                .date(userBlood.getDate())
                .location(userBlood.getLocation())
                .number(userBlood.getNumber())
                .type(userBlood.getType())
                .build();
    }

    public static UserBloodResponse.UserBloodListDTO userBloodListDTO(List<UserBlood> userBloodList) {
        List<UserBloodResponse.UserBloodResultDTO> reviewPreViewDTOList = userBloodList.stream()
                .map(UserBloodConverter::userBloodResultDTO).collect(Collectors.toList());
        return UserBloodResponse.UserBloodListDTO.builder()
                .userBloodList(reviewPreViewDTOList).build();
    }

    public static UserBloodResponse.DayDTO dayDTO(Long left) {
        return UserBloodResponse.DayDTO.builder()
                .LeftDay(left)
                .build();
    }
}
