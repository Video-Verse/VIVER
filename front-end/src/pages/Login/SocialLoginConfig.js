import { API_BASE_URL } from "./apiConfig";


export function socialLoginConfig(userType) {
	const kakaoClientId = process.env.REACT_APP_KAKAO_CLIENT_ID;
	const naverClientId = process.env.REACT_APP_NAVER_CLIENT_ID;
	

	const kakaoRedirectUri = API_BASE_URL + process.env.REACT_APP_KAKAO_REDIRECT_URI;
	const naverRedirectUri = API_BASE_URL + "/oauth/naver/callback";

	const naverState = "state";
	const responseType = "code";

    const kakaoUrl = process.env.REACT_APP_KAKAO_URI + "?client_id=" + kakaoClientId + "&redirect_uri=" + kakaoRedirectUri + "&response_type=code";
    const naverUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" + naverClientId + "&state=" + naverState + "&redirect_uri=" + naverRedirectUri;
    
	var url = "";

	if (userType == "kakao") {
		url = kakaoUrl;
	} else if (userType == "naver") {
		url = naverUrl;
	} else {

	}
	window.location.href = url;
}

