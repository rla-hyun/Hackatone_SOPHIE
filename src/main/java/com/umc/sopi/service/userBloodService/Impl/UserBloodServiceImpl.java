package com.umc.sopi.service.userBloodService.Impl;

import com.umc.sopi.converter.UserBloodConverter;
import com.umc.sopi.domain.User;
import com.umc.sopi.domain.UserBlood;
import com.umc.sopi.repository.UserBloodRepository;
import com.umc.sopi.repository.UserRepository;
import com.umc.sopi.service.userBloodService.UserBloodService;
import com.umc.sopi.web.dto.UserBloodRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBloodServiceImpl implements UserBloodService {
    private final UserRepository userRepository;
    private final UserBloodRepository userBloodRepository;

    @Transactional
    @Override
    public UserBlood registerBlood(UserBloodRequest.RegisterDTO registerDTO){
        UserBlood userBlood = UserBloodConverter.toUserBlood(registerDTO);
        User user = userRepository.findById(1L).get();
        userBlood.setUser(user);
        if(userBlood.getType().equals("전혈")){
            user.wholeAdd();
        } else if (userBlood.getType().equals("성분")) {
            user.ingredientAdd();
        }
        user.meritAdd();
        userRepository.save(user);
        return userBloodRepository.save(userBlood);
    }

//    @Override
//    public List<UserBloodResponse.UserBloodResultDTO> userBloodList(Long userId){
//        List<Long> longs = Arrays.asList(userId, userId);
//        List<UserBlood> toUserBloodList = userBloodRepository.findAllById(longs);
//        System.out.println(toUserBloodList);
//        List<UserBloodResponse.UserBloodResultDTO> userBloodsDTOList = new ArrayList<>();
//        for(UserBlood userBloods : toUserBloodList){
//            UserBloodResponse.UserBloodResultDTO userBloodResultDTO= UserBloodResponse.UserBloodResultDTO.builder()
//                    .type(userBloods.getType())
//                    .location(userBloods.getLocation())
//                    .number(userBloods.getNumber())
//                    .date(userBloods.getDate())
//                    .build();
//            userBloodsDTOList.add(userBloodResultDTO);
//        }
//        return userBloodsDTOList;
//    }

    @Override
    public List<UserBlood> userBloodList(Long userId){
        Optional<User> user = userRepository.findById(userId);
        List<UserBlood> toUserBloodList = userBloodRepository.findAllByUser(user.get());
        return toUserBloodList;
    }

    @Override
    public Long leftDayBlood(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        List<UserBlood> dayList = userBloodRepository.findAllByUserOrderByDateAsc(user.get());
        int idx = dayList.size() - 1; // 마지막 데이터
        String type = dayList.get(idx).getType();
        LocalDate left = dayList.get(idx).getDate();
        LocalDate startDate = LocalDate.now();
        Long days = ChronoUnit.DAYS.between(left, startDate);

        if (type.equals("전혈")) {
            days -= 56;
        } else if (type.equals("성분")) {
            days -= 14;
        }
        return days;
    }
}
