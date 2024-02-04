
import React, { useState, useEffect, useRef } from "react";
import $ from 'jquery';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Header from "../../components/Header/Header";
import './Join.css';
import CommonInp from "../../components/input/input";
import CommonBtn from "../../components/button/button";
import { useLoginInfo } from "../../context/LoginInfoContext";

const Join = () => {
	useEffect(() => {
		$("#title").css('display', 'none');
		$("#logo").css('display', 'none');
		$("#btn-search").css('visibility', 'hidden');
	}, []);

	const [nickname, setNickname] = useState('');
	const [error, setError] = useState(null);
	const [isBtnDisabled, setIsBtnDisabled] = useState(true);
	//const { loginInfo, setLoginInfo } = useLoginInfo();
	const navigate = useNavigate();
	const inputRef = useRef(null);

	var inputNickname = '';
	//닉네임 정규식 체
	const nicknameRegEx = /^[ㄱ-ㅎ가-힣a-zA-Z0-9]{2,10}$/
	const handleInputChange = (e) => {
		inputNickname = e.target.value;
		setNickname(inputNickname);
		if (!validate(inputNickname, nicknameRegEx)) {
			setIsBtnDisabled(true);
		} else {
			setIsBtnDisabled(false);
		}
	};

	const validate = (value, regEx) => {
		if (!regEx.test(value)) {
			setError("2 ~ 10자 이내의 영문 대소문자, 한글, 숫자만 사용 가능합니다.");
			setIsBtnDisabled(true);
			inputRef.current && inputRef.current.focus();
			return false;
		} else {
			setError(null);
			setIsBtnDisabled(false);
			return true;
		}
	};

	const handleSubmit = (e) => {
		axios.post(
			process.env.REACT_APP_BACK_BASE_URI + '/join'
			, {
				nickname
				//,oauthAttributes : loginInfo.oauthAttributes,
			}).then(response => {
				var code = response.data.code;
				var user = response.data.user;
				var userId = user.userId;
				var nickName = user.nickName;
				if ("000" === code) {
					localStorage.setItem("userId", userId);
					localStorage.setItem("nickName", nickName);
					navigate('/complete')
				} else {
					setError("중복된 닉네임 입니다. 다시 설정해주세요.");
					setIsBtnDisabled(true);
					inputRef.current && inputRef.current.focus();
				}
			});
	}

	return (
		<div>
			<Header />

			<div className="wrap">
				<div className="content">
					<h3 className="content-title mt50">
						비버에서 사용할<br />
						닉네임을 입력해 주세요
					</h3>
					<p className="sub-txt">* 2 ~ 10자 이내의 영문 대소문자, 한글, 숫자만 입력해주세요.</p>
					<CommonInp ref={inputRef} value={nickname} onChange={handleInputChange} />
					{error && <p className="err-msg">{error}</p>}
				</div>
				{/* <CommonBtn onClick={handleSubmit} buttonText="확인"/> */}
				<CommonBtn buttonText="확인" disabled={isBtnDisabled} onClick={handleSubmit} />

			</div>
		</div>
	)
}

export default Join;