package com.umc.sopi.repository;

import com.umc.sopi.domain.User;
import com.umc.sopi.domain.UserBlood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBloodRepository extends JpaRepository<UserBlood, Long> {
    List<UserBlood> findAllByUser(User user);

    List<UserBlood> findAllByUserOrderByDateAsc(User user);
}
