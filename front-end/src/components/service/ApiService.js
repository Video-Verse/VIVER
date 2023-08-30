import { API_BASE_URL } from "../../apiConfig";


export function socialLogin(userType) {
	const clientId = "7e6f9285b35e1c5af2ae9e3da753d37e";
	const redirectUri = API_BASE_URL + "/oauth/kakao/callback";
	const responseType = "code";
	window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=7e6f9285b35e1c5af2ae9e3da753d37e&redirect_uri=http://localhost:8080/oauth/kakao/callback&response_type=code";
}

