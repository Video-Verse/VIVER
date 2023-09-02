package com.project.viver.common.constraint.oauth;

import com.project.viver.common.constraint.Role;
import com.project.viver.common.constraint.UserType;
import com.project.viver.entity.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter @Builder
public class OAuthAttributes {

	private String userId;
    private String name;
    private String profile;
    private UserType userType;
    
    public User toUserEntity(UserType userType, Role role) {
        return User.builder()
                .userType(userType)
                .userId(userId)
                .profile(profile)
                .role(role)
                .build();
    }
}
