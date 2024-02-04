import React, { useState, useEffect } from "react";
import $ from 'jquery';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Header from "../../components/Header/Header";
import CommonInp from "../../components/input/input";
import CommonBtn from "../../components/button/button";


const Login = () => {

    useEffect(() => {
        $("#title").css('display', 'none');
        $("#btn-back").css('display', 'block');
        $("#btn-search").css('visibility', 'hidden');
    });

    const [nickname, setNickname] = useState('');
    const [error, setError] = useState(null);
	const [isBtnDisabled, setIsBtnDisabled] = useState(true);
    const navigate = useNavigate();

    const handleSubmit = (e) => {
		axios.post(
			process.env.REACT_APP_BACK_BASE_URI + '/api/users/registerNickname'
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
					navigate('/complete', { state: nickName })
				} else {
					setError("중복된 닉네임 입니다. 다시 설정해주세요.");
					setIsBtnDisabled(true);
					// inputRef.current && inputRef.current.focus();
				}
			});
	}


    return (
		<div>
			<Header />

			<div className="wrap">
				<div className="content">
					<h3 className="content-title mt50">
						닉네임을 입력해 주세요
					</h3>
					<CommonInp value={nickname}/>
					{error && <p className="err-msg">{error}</p>}
				</div>
				<CommonBtn buttonText="확인" disabled={isBtnDisabled} onClick={handleSubmit}/>
				
			</div>
		</div>
	)
};

export default Login;