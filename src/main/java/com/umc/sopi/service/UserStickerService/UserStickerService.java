package com.umc.sopi.service.UserStickerService;

import com.umc.sopi.domain.StickerImage;

import java.util.List;

public interface UserStickerService {

    List<StickerImage> findSticker(Long userId);
}
