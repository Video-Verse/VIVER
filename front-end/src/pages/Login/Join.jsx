
import React, { useState } from "react";
import axios from 'axios';
import Header from "../../components/Header/Header";
import { API_BASE_URL } from "../../apiConfig";
import './Join.css';
import CommonInp from "../../components/input/input";
import CommonBtn from "../../components/button/button";
import { useLoginInfo } from "../../context/LoginInfoContext";

const Join = () => {
	const [nickname, setNickname] = useState('');
	const [error, setError] = useState(null);
	const { loginInfo, setLoginInfo } = useLoginInfo();

	const handleInputChange = (e) => {
		setNickname(e.target.value);
	}
 	console.log('login정보',loginInfo);
	const handleSubmit = () => {
		console.log('loginInfo', loginInfo);
		if (nickname.length < 2 || nickname.length > 10) {
			setError("2 ~ 10자 이내로 입력해주세요.");
		} else {
			setError(null);
			axios.post(API_BASE_URL+'/api/users/registerNickname', {
				nickname,
				oauthAttributes: loginInfo.oauthAttributes,
			}).then(response => {
				alert('success');
				console.log('Success:', response.data);
			}).catch(error => {
				alert('error');
				console.error('Error:', error);
			});
		}
	}
	return (
		<div>
			<Header />

			<div className="wrap">
				<div className="content">
					<h3 className="content-title">
						비버에서 사용할<br />
						닉네임을 입력해 주세요
					</h3>
					<p className="sub-txt">* 2 ~ 10자 이내로 입력해주세요.</p>
					<CommonInp value={nickname} onChange={handleInputChange} />
					{error && <p className="err-msg">{error}</p>}
				</div>
				<CommonBtn onClick={handleSubmit} />
				
			</div>
		</div>
	)
}

export default Join;