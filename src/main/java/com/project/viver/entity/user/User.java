package com.project.viver.entity.user;

import java.time.LocalDateTime;

import com.project.viver.common.constraint.Role;
import com.project.viver.common.constraint.UserType;
import com.project.viver.common.util.DateTimeUtils;
import com.project.viver.dto.token.JwtTokenDto;
import com.project.viver.entity.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    private String userId;
    
    private String phoneNumber;
    private String nickName;
    private String levelId;
    private String kakao;
    private String naver;
    private String withdrawalStatus;
   

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private UserType memberType;
    //private String password;
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;
    private String refreshToken;
    private LocalDateTime tokenExpirationTime;
    
    @Builder
    public User(String userId, String phoneNumber, String nickName, String withdrawalStatus, UserType memberType,
    		String password, String profile, Role role) {
    	this.userId = userId;
    	this.phoneNumber = phoneNumber;
    	this.nickName = nickName;
    	this.withdrawalStatus = withdrawalStatus;
    	this.memberType = memberType;
    	this.profile = profile;
    	this.role = role;
    }
    

    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }

}