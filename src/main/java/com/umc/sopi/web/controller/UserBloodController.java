package com.umc.sopi.web.controller;

import com.umc.sopi.apiPayload.ApiResponse;
import com.umc.sopi.converter.UserBloodConverter;
import com.umc.sopi.domain.UserBlood;
import com.umc.sopi.service.userBloodService.UserBloodService;
import com.umc.sopi.web.dto.UserBloodRequest;
import com.umc.sopi.web.dto.UserBloodResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bloodcard")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserBloodController {
    private final UserBloodService userBloodService;
    @PostMapping("/")
    @Operation(summary = "헌혈증서 등록 API", description = "새로운 헌혈증서를 등록하는 API이며, userId를 반환한다")
    @Parameters({
            @Parameter(name = "type", description = "혈액형"),
            @Parameter(name = "number", description = "헌혈증서 번호"),
            @Parameter(name = "date", description = "헌혈증서 등록일"),
            @Parameter(name = "location", description = "헌혈원명")
    })
    public ApiResponse<UserBloodResponse.RegisterUserBloodResultDTO> createReview(@RequestBody UserBloodRequest.RegisterDTO request){
        UserBlood userBlood = userBloodService.registerBlood(request);
        return ApiResponse.onSuccess(UserBloodConverter.toRegisterUserBloodResultDTO(userBlood));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "유저가 등록한 헌혈증서 리스트 조회 API", description = "유저가 등록한 헌혈증서리스트를 반환하는 API이다")
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디를 path variable로 받는다.")
    })
    public ApiResponse<UserBloodResponse.UserBloodListDTO> createReview(@PathVariable(name = "userId") Long userId){
        List<UserBlood> userBloodList = userBloodService.userBloodList(userId);
        return ApiResponse.onSuccess(UserBloodConverter.userBloodListDTO(userBloodList));
    }

    @GetMapping("/left-day/{userId}")
    @Operation(summary = "헌혈 남은 기간 조회 API", description = "유저가 헌혈할 수 있기까지 남은 날을 반환한다.")
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디를 path variable로 받는다.")
    })
    public ApiResponse<UserBloodResponse.DayDTO> leftDay(@PathVariable(name = "userId") Long userId) {
        Long days = userBloodService.leftDayBlood(userId);
        return ApiResponse.onSuccess(UserBloodConverter.dayDTO(days));
    }
}
