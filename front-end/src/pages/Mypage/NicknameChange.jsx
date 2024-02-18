import React, { useState, useEffect, useRef } from "react";
import $ from 'jquery';
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import Header from "../../components/Header/Header";
import CommonInp from "../../components/input/input";
import CommonBtn from "../../components/button/button";


const NicknameChange = () => {
	const [nickname, setNickname] = useState('');
	const [error, setError] = useState(null);
	const [isBtnDisabled, setIsBtnDisabled] = useState(true);
	//const { loginInfo, setLoginInfo } = useLoginInfo();
	const inputRef = useRef(null);
	const navigate = useNavigate();

	var inputNickname = '';
	//nickname check
	const nicknameRegEx = /^[ㄱ-ㅎ가-힣a-zA-Z0-9]{2,10}$/
	const handleInputChange = (e) => {
		inputNickname = e.target.value;
		setNickname(inputNickname.slice(0, 10));
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
			process.env.REACT_APP_BACK_BASE_URI + '/mypage/nicknameChange'
			, {
				nickname : nickname
				, userId : localStorage.getItem("userId")
				//,oauthAttributes : loginInfo.oauthAttributes,
			}).then(response => {
				var code = response.data.code;
				if ("000" === code) {
					var user = response.data.user;
					var userId = user.userId;
					var nickname = user.nickname;
				
					localStorage.setItem("userId", userId);
					localStorage.setItem("nickname", nickname);
					
					navigate('/mypage');
					
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
					
                    <span className="inp-tit">닉네임</span>
					<CommonInp ref={inputRef} value={nickname} onChange={handleInputChange} />
					<p className="sub-txt">* 2 ~ 10자 이내로 입력해주세요.</p>
					{error && <p className="err-msg">{error}</p>}
				</div>
				<CommonBtn buttonText="확인" disabled={isBtnDisabled} onClick={handleSubmit}/>
				
			</div>
		</div>
	)
}

export default NicknameChange;