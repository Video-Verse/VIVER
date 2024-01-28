
import React, { useState, useEffect } from "react";
import $ from 'jquery';
import axios from 'axios';
import Header from "../../components/Header/Header";
import { API_BASE_URL } from "./apiConfig";
import './Join.css';
import CommonInp from "../../components/input/input";
import CommonBtn from "../../components/button/button";
import { useLoginInfo } from "../../context/LoginInfoContext";

const Join = () => {
    useEffect(() => {
        $("#title").css('display', 'none');
        $("#logo").css('display', 'none');
		$("#btn-search").css('visibility', 'hidden');
    },[]);

	const [nickname, setNickname] = useState('');
	const [error, setError] = useState(null);
    const [isBtnDisabled, setIsBtnDisabled] = useState(true);
	const { loginInfo, setLoginInfo } = useLoginInfo();

	const handleInputChange = (e) => {
        const inputNickname = e.target.value;
        setNickname(inputNickname);

        if (inputNickname.length < 2 || inputNickname.length > 10) {
            setError("2 ~ 10자 이내로 입력해주세요.");
            setIsBtnDisabled(true);
        } else {
            setError(null);
            setIsBtnDisabled(false);
            // axios.post(API_BASE_URL+'/api/users/registerNickname', {
            //     nickname,
            //     oauthAttributes: loginInfo.oauthAttributes,
            // }).then(response => {
            //     alert('success');
            //     console.log('Success:', response.data);
            // }).catch(error => {
            //     alert('error');
            //     console.error('Error:', error);
            // });
        }
	}


 	console.log('login정보',loginInfo);
	// const handleSubmit = () => {
		
	// }
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
				{/* <CommonBtn onClick={handleSubmit} buttonText="확인"/> */}
				<CommonBtn buttonText="확인" disabled={isBtnDisabled}/>
				
			</div>
		</div>
	)
}

export default Join;