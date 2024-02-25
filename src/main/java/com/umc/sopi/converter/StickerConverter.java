package com.umc.sopi.converter;

import com.umc.sopi.domain.StickerImage;
import com.umc.sopi.domain.User;
import com.umc.sopi.domain.UserSticker;
import com.umc.sopi.web.dto.StickerResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StickerConverter {

    public static StickerResponseDTO.stickerResultDTO toStickerDTO(StickerImage image) {
        return StickerResponseDTO.stickerResultDTO.builder()
                .name(image.getName())
                .imageUrl(image.getUrl())
                .build();
    }

    public static UserSticker toUserSticker(StickerImage image, User user) {
        return UserSticker.builder()
                .user(user)
                .image(image)
                .build();
    }

    public static StickerResponseDTO.stickerInfoDTO stickerInfoDTO(StickerImage stickerImage) {
        return StickerResponseDTO.stickerInfoDTO.builder()
                .name(stickerImage.getName())
                .url(stickerImage.getUrl())
                .build();
    }

    public static StickerResponseDTO.stickerListResultDTO stickerListResultDTO(List<StickerImage> imageList) {
        List<StickerResponseDTO.stickerInfoDTO> stickerListResultDTOList = imageList.stream()
                .map(StickerConverter::stickerInfoDTO).collect(Collectors.toList());

        return StickerResponseDTO.stickerListResultDTO.builder()
                .imageInfoList(stickerListResultDTOList)
                .build();
    }
}
