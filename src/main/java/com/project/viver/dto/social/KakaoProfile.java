package com.project.viver.dto.social;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoProfile {

	private Long id;
    private Properties properties;
    private Kakao_account kakao_account;

    @Getter
    @Setter
    @ToString
	public static class Properties {
        private String nickname;
        private String thumbnail_image;
        private String profile_image;
    }

    @Getter
    @Setter
    @ToString
    public static class Kakao_account {
    	private String nickname;
    	private String thumbnail_image_url;
    	private String profile_image_url;
    	private String email;
    }
}
