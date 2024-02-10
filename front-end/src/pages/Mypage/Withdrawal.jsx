import React, { useEffect } from "react";
import $ from 'jquery';
import Header from "../../components/Header/Header";
import CommonBtnType2 from "../../components/button/buttonType2";


const Withdrawal = () => {
    useEffect(() => {
		$("#title").text("회원탈퇴");
		$("#logo").css('display', 'none');
		$("#btn-search").css('visibility', 'hidden');
	}, []);

    return(
        <div>
            <Header/>

            <div className="wrap">
                <div className="content withdrawal">
                    <h3 className="content-title">비버를 <span className="fc-red">탈퇴</span>하시나요?</h3>

                    <ul className="notice-list">
                        <li>
                            지금 탈퇴하시면 사용하신 모든 기록이 전부 사라지게 됩니다. 추후 동일 계정으로 재가입하셔도 기록은 복구되지 않습니다.
                        </li>
                        <li>
                            탈퇴 직후부터 해당 계정으로는 일주일 동안 모든 서비스 사용이 불가합니다. 탈퇴 신청 전에 꼭 확인해 주세요.
                        </li>
                    </ul>
                    <div className="check-box">
                        <input type="checkbox" id="check1" name="agree"/>
                        <label htmlFor="check1">회원 탈퇴 안내사항을 확인하였으며 동의합니다.</label>
                    </div>

                    <p className="fs-20 fw-bold mt30">탈퇴하시는 이유를 알려주세요.</p>
                    <p className="sub-txt">
                        서비스 탈퇴 사유에 대해 알려주세요.<br/>
                        소중한 피드백을 담아 더 나은 서비스로 보답드리겠습니다.
                    </p>
                    <div className="select-box">
                        <span>선택해주세요.</span>
                    </div>
                </div>
            </div>
            
            <CommonBtnType2
                buttonText1="취소하기"
                buttonText2="탈퇴하기"
            />

        </div>
    );
}

export default Withdrawal;