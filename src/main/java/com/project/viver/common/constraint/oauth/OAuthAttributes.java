package com.project.viver.common.constraint.oauth;

import com.project.viver.common.constraint.Role;
import com.project.viver.common.constraint.UserType;
import com.project.viver.entity.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter 
public class OAuthAttributes {

	private String userId;
    private String name;
    private String profile;
    private UserType userType;
    
    
    @Builder
    public OAuthAttributes(String userId, String name, String profile, UserType userType) {
        this.userId = userId;
        this.name = name;
        this.profile = profile;
        this.userType = userType;
    }
    

    public User toUserEntity(UserType userType, Role role) {
        return User.builder()
                .userType(userType)
                .userId(userId)
                .profile(profile)
                .role(role)
                .build();
    }



}
