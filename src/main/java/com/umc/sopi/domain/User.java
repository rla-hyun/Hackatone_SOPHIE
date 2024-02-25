package com.umc.sopi.domain;

import com.umc.sopi.domain.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birth;

    private String blood;

    private Long draw;

    private Integer merit;

    private Integer whole;

    private Integer ingredient;

    private String imageUrl;

    private String gender;


    public void wholeAdd(){
        this.whole += 1;
    }

    public void meritAdd(){
        this.merit += 1;
    }
    public void ingredientAdd(){
        this.ingredient += 1;
    }
}

