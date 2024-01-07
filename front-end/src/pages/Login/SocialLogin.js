import { API_BASE_URL } from "./apiConfig";


export function socialLogin(userType) {
    const kakaoClientId = "7e6f9285b35e1c5af2ae9e3da753d37e";
    const naverClientId = "pHlFjtbzs_yxJID83EcM"; 

	const kakaoRedirectUri = API_BASE_URL + "/oauth/kakao/callback";
    const naverRedirectUri = API_BASE_URL + "/oauth/naver/callback";
    
    const naverState = "";
    const responseType = "code";

	// const clientId = "7e6f9285b35e1c5af2ae9e3da753d37e";
	// const redirectUri = API_BASE_URL + "/oauth/kakao/callback";
    // const responseType = "code";

    const kakaoUrl = "https://kauth.kakao.com/oauth/authorize?client_id={kakaoClientId}&redirect_uri={API_BASE_URL + `/oauth/kakao/callback`}&response_type=code";
    const naverUrl = "https://nid.naver.com/oauth2.0/authorize?state=1234&client_id={naverClientId}&redirect_uri={API_BASE_URL + `/oauth/naver/callback`}&response_type=code";

    window.location.href = naverUrl;
}

