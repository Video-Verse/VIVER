package com.project.viver.service.user;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.viver.common.constraint.oauth.OAuthAttributes;
import com.project.viver.dto.user.NicknameRequest;
import com.project.viver.entity.user.User;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.AuthenticationException;
import com.project.viver.error.exception.BusinessException;
import com.project.viver.error.exception.EntityNotFoundException;
import com.project.viver.repository.user.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
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
    public User findUserByRefreshToken(String refreshToken) {
    	User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
        LocalDateTime tokenExpirationTime = user.getTokenExpirationTime();
        if(tokenExpirationTime.isBefore(LocalDateTime.now())) {
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
        return user;
    }

    public User findUserByUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    }

	public ResponseEntity<String> registerNickname(NicknameRequest request) {
        OAuthAttributes oauthAttributes = request.getOauthAttributes();
		Optional<User> user =  userRepository.findByUserId(oauthAttributes.getUserId());
		System.out.println(request.getNickname());
		//닉네임 중복체크 추가하기
			//return new ResponseEntity<>("duplicate", HttpStatus.BAD_REQUEST);
		user.get().changeNickName(request.getNickname());
		return new ResponseEntity<>("uccess", HttpStatus.OK);		
	}
	
	/**
	 * kakao로 찾기
	 * 
	 * @param kakaoEmail
	 * @return
	 */
	@Transactional(readOnly = true)
    public Optional<User> findByKakao(String kakaoEmail) {
        return userRepository.findByKakao(kakaoEmail);
    }

//    public Object doSocialLogin(@Valid SocialLoginRequest request) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'doSocialLogin'");
//    }
}