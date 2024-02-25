package com.umc.sopi.service.userService;

import com.umc.sopi.domain.User;
import com.umc.sopi.web.dto.UserRequestDTO;
import com.umc.sopi.web.dto.UserResponse;

public interface UserService {
    public void updateWhole();
    public void updateMerit();
    public void updateIngredient();
    public Long getWhole();
    public Long getMerit();
    public Long getIngredient();
    public UserResponse.MyPageDTO myPage(Long userID);

    public User createUser(UserRequestDTO.UserDTO request);
}
