package com.umc.sopi.service.userService.Impl;

import com.umc.sopi.converter.UserConverter;
import com.umc.sopi.domain.User;
import com.umc.sopi.repository.UserRepository;
import com.umc.sopi.service.userService.UserService;
import com.umc.sopi.web.dto.UserRequestDTO;
import com.umc.sopi.web.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public void updateWhole() {
        User user = userRepository.findById(1L).orElseThrow(()->
                new IllegalStateException("해당 유저가 존재하지 않습니다."));
        user.wholeAdd();
    }

    @Override
    @Transactional
    public void updateMerit() {
        User user = userRepository.findById(1L).orElseThrow(()->
                new IllegalStateException("해당 유저가 존재하지 않습니다."));
        user.meritAdd();
    }

    @Override
    @Transactional
    public void updateIngredient() {
        User user = userRepository.findById(1L).orElseThrow(()->
                new IllegalStateException("해당 유저가 존재하지 않습니다."));
        user.ingredientAdd();
    }

    @Override
    public Long getWhole(){
        User user = userRepository.findById(1L).orElseThrow(()->
                new IllegalStateException("해당 유저가 존재하지 않습니다."));
        return user.getWhole().longValue();
    }
    @Override
    public Long getMerit(){
        User user = userRepository.findById(1L).orElseThrow(()->
                new IllegalStateException("해당 유저가 존재하지 않습니다."));
        return user.getMerit().longValue();
    }

    @Override
    public Long getIngredient(){
        User user = userRepository.findById(1L).orElseThrow(()->
                new IllegalStateException("해당 유저가 존재하지 않습니다."));
        return user.getIngredient().longValue();
    }

    @Override
    public UserResponse.MyPageDTO myPage(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->
                new IllegalStateException("해당 유저가 존재하지 않습니다."));

        UserResponse.MyPageDTO result = UserResponse.MyPageDTO.builder()
                .blood(user.getBlood())
                .whole(user.getWhole())
                .birth(user.getBirth())
                .name(user.getName())
                .merit(user.getMerit())
                .ingredient(user.getIngredient())
                .imageUrl(user.getImageUrl())
                .gender(user.getGender())
                .build();
        return result;
    }

    @Override
    public User createUser(UserRequestDTO.UserDTO request) {
        User user = UserConverter.toUserDTO(request);
        return userRepository.save(user);
    }
}
