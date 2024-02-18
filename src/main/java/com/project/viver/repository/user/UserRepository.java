package com.project.viver.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByRefreshToken(String refreshToken);
    
    Optional<User> findByKakao(String kakaoEmail);
    
    User findByNickname(String nickName);
}
