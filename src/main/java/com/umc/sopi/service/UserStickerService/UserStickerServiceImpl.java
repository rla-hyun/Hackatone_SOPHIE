package com.umc.sopi.service.UserStickerService;

import com.umc.sopi.domain.StickerImage;
import com.umc.sopi.domain.User;
import com.umc.sopi.domain.UserSticker;
import com.umc.sopi.repository.StickerImageRepository;
import com.umc.sopi.repository.UserRepository;
import com.umc.sopi.repository.UserStickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserStickerServiceImpl implements UserStickerService{

    private final UserStickerRepository userStickerRepository;
    private final StickerImageRepository stickerImageRepository;
    private final UserRepository userRepository;

    @Override
    public List<StickerImage> findSticker(Long userId) {
        User user = userRepository.findById(userId).get();
        List<UserSticker> userStickerList = userStickerRepository.findAllByUser(user);

        List<StickerImage> stickerImageList = userStickerList.stream()
                .map(UserSticker::getImage) // UserSticker에서 StickerImage로 매핑
                .collect(Collectors.toList());

        return stickerImageList;
    }
}
