package com.umc.sopi.service.StickerImageService;

import com.umc.sopi.domain.StickerImage;

public interface StickerImageService {

    StickerImage addSticker(Long stickerId, Long userId);

    Long getRamdomStickerId();
}
