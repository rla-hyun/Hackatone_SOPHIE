package com.umc.sopi.web.controller;

import com.umc.sopi.apiPayload.ApiResponse;
import com.umc.sopi.converter.UserConverter;
import com.umc.sopi.domain.User;
import com.umc.sopi.service.userService.UserService;
import com.umc.sopi.web.dto.UserRequestDTO;
import com.umc.sopi.web.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;

    @PostMapping ("/user")
    @Operation(summary = "유저 등록 API", description = "새로운 유저를 등록하는 API이며, userId를 반환한다")
    @Parameters({
            @Parameter(name = "name", description = "유저의 이름"),
            @Parameter(name = "gender", description = "유저의 성별, 한글로 받습니다."),
            @Parameter(name = "birth", description = "유저의 생년월일."),
            @Parameter(name = "blood", description = "유저의 혈액형."),
            @Parameter(name = "draw", description = "유저의 누적 헌혈 수.")
    })
    public ApiResponse<UserResponse.userIdDTO> joinUser(@RequestBody UserRequestDTO.UserDTO request) {
        User user = userService.createUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinUserDTO(user));

    }

    @GetMapping("/count/whole")
    @Operation(summary = " API", description = "새로운 유저를 등록하는 API이며, userId를 반환한다")
    public ApiResponse<UserResponse.CountDTO> getWhole(){
        return ApiResponse.onSuccess(UserConverter.toCountResultDTO(userService.getWhole()));
    }
    @GetMapping("/count/merit")
    @Operation(summary = "전체 헌혈 누적 횟수 API", description = "유저가 헌혈한 전체 횟수를 반환하는 API이다")
    public ApiResponse<UserResponse.CountDTO> getMerit(){
        return ApiResponse.onSuccess(UserConverter.toCountResultDTO(userService.getMerit()));
    }
    @GetMapping("/count/ingredient")
    @Operation(summary = "일년간 성분 헌혈 횟수 API", description = "유저가 일년동안 성분 헌혈한 횟수를 반환하는 API이다")
    public ApiResponse<UserResponse.CountDTO> getIngredient(){
        return ApiResponse.onSuccess(UserConverter.toCountResultDTO(userService.getIngredient()));
    }
    @GetMapping("/count/year")
    @Operation(summary = "일년간 성분 헌혈 횟수 API", description = "유저가 일년동안 성분 헌혈한 횟수를 반환하는 API이다")
    public ApiResponse<UserResponse.CountDTO> getYear(){
        return ApiResponse.onSuccess(UserConverter.toCountResultDTO(userService.getIngredient() + userService.getWhole()));
    }

    @GetMapping("/MyPage/{userId}")
    @Operation(summary = "마이페이지 조회 API", description = "마이페이지에 표시할 유저의 정보를 반환하는 API이다")
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디를 path variable로 받는다.")
    })
    public ApiResponse<UserResponse.MyPageDTO> getMyPage(@PathVariable(name = "userId") Long userId){
        return ApiResponse.onSuccess(userService.myPage(userId));

    }
}
