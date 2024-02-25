package com.umc.sopi.web.controller;

import com.umc.sopi.service.StickerImageService.StickerImageService;
import com.umc.sopi.service.UserStickerService.UserStickerService;
import com.umc.sopi.apiPayload.ApiResponse;
import com.umc.sopi.converter.StickerConverter;
import com.umc.sopi.domain.StickerImage;
import com.umc.sopi.web.dto.StickerResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sticker")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StickerController {

    private final StickerImageService stickerImageService;
    private final UserStickerService userStickerService;

    @PostMapping("/draw")
    @Operation(summary = "스티커 뽑기 API", description = "스티커를 뽑는 API이며, 새로운 스티커는 저장, 중복된 스티커는 무시한다.")
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디를 쿼리스트링으로 받는다.")
    })
    public ApiResponse<StickerResponseDTO.stickerResultDTO> stickerDraw(@RequestParam(name = "userId") Long userId) {
        Long stickerId = stickerImageService.getRamdomStickerId();
        StickerImage image = stickerImageService.addSticker(stickerId, userId);
        return ApiResponse.onSuccess(StickerConverter.toStickerDTO(image));
    }

    @GetMapping("/board/{userId}")
    @Operation(summary = "스티커 조회 API", description = "유저가 얻은 스티커 목록을 리스트로 반환하는 API이다.")
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디를 path variable로 받는다.")
    })
    public ApiResponse<StickerResponseDTO.stickerListResultDTO> stickerList(@PathVariable(name = "userId") Long userId) {
        List<StickerImage> list = userStickerService.findSticker(userId);
        return ApiResponse.onSuccess(StickerConverter.stickerListResultDTO(list));
    }
}