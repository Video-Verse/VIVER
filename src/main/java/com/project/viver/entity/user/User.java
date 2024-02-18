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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "TB_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    private String userId;
    
    private String phoneNumber;
    private String nickname;
    private String levelId;
    private String kakao;
    private String naver;
    private String kakaoId;
    //private String withdrawalStatus;
   

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private UserType userType;
    //private String password;
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;
    private String refreshToken;
    private LocalDateTime tokenExpirationTime;
    private String withdrwalReason;
    
    @Builder
    public User(String userId, String phoneNumber, String nickname, String withdrawalStatus, UserType userType,
    		String password, String profile, Role role, String kakao, String naver, String kakaoId) {
    	this.userId = userId;
    	this.phoneNumber = phoneNumber;
    	this.nickname = nickname;
    	//this.withdrawalStatus = withdrawalStatus;
    	this.userType = userType;
    	this.profile = profile;
    	this.role = role;
    	this.kakao = kakao;
    	this.naver = naver;
    	this.kakaoId = kakaoId;
    	
    }
    

    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }
    
    
    public void changeNickName(String newNickName) {
        this.nickname = newNickName;
    }


}