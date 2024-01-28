import React, { useState, useEffect } from "react";
import $ from 'jquery';

import Header from "../../components/Header/Header";
import CommonInp from "../../components/input/input";
import CommonBtn from "../../components/button/button";


const NicknameChange = () => {
    useEffect(() => {
        $("#title").text("닉네임 변경");
        $("#logo").css('display', 'none');
		$("#btn-search").css('visibility', 'hidden');
    },[]);

	const [nickname, setNickname] = useState('');
	const [error, setError] = useState(null);
    const [isBtnDisabled, setIsBtnDisabled] = useState(true);

	const handleInputChange = (e) => {
        const inputNickname = e.target.value;
        setNickname(inputNickname);

        if (inputNickname.length < 2 || inputNickname.length > 10) {
            setError("2 ~ 10자 이내로 입력해주세요.");
            setIsBtnDisabled(true);
        } else {
            setError(null);
            setIsBtnDisabled(false);
          
        }
	}

    return (
		<div>
			<Header />

			<div className="wrap">
				<div className="content">
					
                    <span className="inp-tit">닉네임</span>
					<CommonInp value={nickname} onChange={handleInputChange} />
					<p className="sub-txt">* 2 ~ 10자 이내로 입력해주세요.</p>
					{error && <p className="err-msg">{error}</p>}
				</div>
				<CommonBtn buttonText="확인" disabled={isBtnDisabled}/>
				
			</div>
		</div>
	)
}

export default NicknameChange;