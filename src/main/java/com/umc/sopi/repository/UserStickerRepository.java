package com.umc.sopi.repository;

import com.umc.sopi.domain.StickerImage;
import com.umc.sopi.domain.User;
import com.umc.sopi.domain.UserSticker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStickerRepository extends JpaRepository<UserSticker, Long> {

    List<UserSticker> findAllByUser(User user);

    UserSticker findTopByUserAndImage(User user, StickerImage image);
}
