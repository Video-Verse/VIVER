package com.project.viver.service.user;


import java.time.LocalDateTime;
import java.util.Optional;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.viver.entity.user.User;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.AuthenticationException;
import com.project.viver.error.exception.BusinessException;
import com.project.viver.error.exception.EntityNotFoundException;
import com.project.viver.repository.user.UserRepository;


import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerMember(User user) {
        validateDuplicateMember(user);
        return userRepository.save(user);
    }

    private void validateDuplicateMember(User user) {
        Optional<User> optionalUser = userRepository.findByUserId(user.getUserId());
        if(optionalUser.isPresent()) {
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_MEMBER);
        }
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public User findMemberByRefreshToken(String refreshToken) {
    	User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
        LocalDateTime tokenExpirationTime = user.getTokenExpirationTime();
        if(tokenExpirationTime.isBefore(LocalDateTime.now())) {
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
        return user;
    }

    public User findMemberByMemberId(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    }
}