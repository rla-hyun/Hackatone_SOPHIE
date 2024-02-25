package com.umc.sopi.converter;

import com.umc.sopi.domain.User;
import com.umc.sopi.web.dto.UserRequestDTO;
import com.umc.sopi.web.dto.UserResponse;

public class UserConverter {
    public static UserResponse.CountDTO toCountResultDTO(Long count){
        return UserResponse.CountDTO.builder()
                .count(count)
                .build();
    }

    public static User toUserDTO(UserRequestDTO.UserDTO request) {
        return User.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .draw(request.getDraw())
                .blood(request.getBlood())
                .build();
    }

    public static UserResponse.userIdDTO toJoinUserDTO(User user) {
        return UserResponse.userIdDTO.builder()
                .id(user.getId())
                .build();
    }
}
