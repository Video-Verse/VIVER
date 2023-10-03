import React from "react";
import logo from '../../assets/images/logo.png';
import './search.css';
import "../Common/axios.js";
import axios from "axios";

// import CommonBtn from './components/button/button';

import { Link } from "react-router-dom";
import { socialLogin } from "../../components/service/ApiService";

const Login = () => {
	const handleSocialLogin = (socialType) => {
		socialLogin(socialType);
	}
	return (
		<div className="wrap">
			<div className="content">
				<div className="img-box">
					<h3 className="login-title">나의 취향 저장소</h3>
					<img src={logo} className="logo" alt="logo" />
				</div>
				<div className="btn-group">
					<button type="button" className="btn btn-kakao" onClick={() => handleSocialLogin("kakao")}>
						<span>카카오로 로그인</span>
					</button>

					<button type="button" className="btn btn-naver"><span>네이버로 로그인</span></button>
				</div>
			</div>
		</div>
	);
};

export default Login;




