import React, { useEffect, useState } from "react";
import $ from 'jquery';
import Header from "../../components/Header/Header";
import CommonBtnType2 from "../../components/button/buttonType2";
import BottomSheet from "../../components/bottomsheet/bottomsheet"; // BottomSheet 컴포넌트 추가
import Modal from "../Common/modal";


const Withdrawal = () => {
    useEffect(() => {
		$("#title").text("회원탈퇴");
		$("#logo").css('display', 'none');
		$("#btn-search").css('visibility', 'hidden');
	}, []);

    // 탈퇴 사유 팝업
    const [isBottomOpen, setIsBottomOpen] = useState(false);
    
    // 체크 조건 설정
    const [checkOption, setCheckOption] = useState("");
    const [isOtherOptionChck, setIsOtherOptionChck] = useState(false);

    const checkOptions = (option) => {
        setCheckOption(option);
        if(option === "기타"){
            setIsOtherOptionChck(true);
        } else {
            setIsOtherOptionChck(false);
        }
    };
    
    const popContents = (
        <div className="option-box mt30">
            <div className="radio-box">
                <label htmlFor="Option01" className={checkOption === "탈퇴 후 재가입을 위해서" ? "checked" : ""}>탈퇴 후 재가입을 위해서</label>
                <input type="radio" className="radio" id="Option01" name="checkOption" checked={checkOption === "재가입"} onChange={() => checkOptions("탈퇴 후 재가입을 위해서")} />
            </div>  
            <div className="radio-box">
                <label htmlFor="Option02" className={checkOption === "콘텐츠 내용이 부족해서" ? "checked" : ""}>콘텐츠 내용이 부족해서</label>
                <input type="radio" className="radio" id="Option02" name="checkOption" checked={checkOption === "내용부족"} onChange={() => checkOptions("콘텐츠 내용이 부족해서")} />
            </div> 
            <div className="radio-box">
                <label htmlFor="Option03" className={checkOption === "서비스 이용이 불편해서" ? "checked" : ""}>서비스 이용이 불편해서</label>
                <input type="radio" className="radio" id="Option03" name="checkOption"  checked={checkOption === "이용불편"} onChange={() => checkOptions("서비스 이용이 불편해서")}></input>
            </div> 
            <div className="radio-box">
                <label htmlFor="Option04" className={checkOption === "자주 이용하지 않아서" ? "checked" : ""}>자주 이용하지 않아서</label>
                <input type="radio" className="radio" id="Option04" name="checkOption"  checked={checkOption === "이용안함"} onChange={() => checkOptions("자주 이용하지 않아서")}></input>
            </div> 
            <div className="radio-box">
                <label htmlFor="Option05" className={checkOption === "서비스 및 고객지원이 만족스럽지 않아서" ? "checked" : ""}>서비스 및 고객지원이 만족스럽지 않아서</label>
                <input type="radio" className="radio" id="Option05" name="checkOption"  checked={checkOption === "불만족"} onChange={() => checkOptions("서비스 및 고객지원이 만족스럽지 않아서")}></input>
            </div> 
            <div className="radio-box">
                <label htmlFor="Option06" className={checkOption === "기타" ? "checked" : ""}>기타 (직접입력)</label>
                <input type="radio" className="radio" id="Option06" name="checkOption"  checked={checkOption === "기타"} onChange={() => checkOptions("기타")}></input>
            </div> 
            {isOtherOptionChck && (
                <div className="textarea">
                    <textarea name="탈퇴사유" cols="30" rows="4" placeholder="100자 이내로 입력해 주세요."/>
                </div>
            )}
        </div>
    );
    
    // 탈퇴사유 선택 
    const selectReason = (reason) => {
        closeBottomSheet();
         console.log("Selected Option:", checkOption);
    };

    const openBottomSheet = () => {
        setIsBottomOpen(true);
    };

    const closeBottomSheet = () => {
        setIsBottomOpen(false);
        //console.log("Selected Option:", selectedReason);
    };

    // 탈퇴 확인 팝업
    const [isAlertOpen, setIsAlertOpen] = useState(false);
    const openAlert = () => {
        setIsAlertOpen(true);
    };
    const closeAlert = () => {
        setIsAlertOpen(false);
    };


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
                    <div className="select-box" onClick={openBottomSheet}>
        				<span>{checkOption ? checkOption : "선택해주세요."}</span>
    				</div>
                </div>
            </div>
            
            <CommonBtnType2
                buttonText1="취소하기"
                buttonText2="탈퇴하기"
                onBtnClick2={openAlert}
            />
          
            {isBottomOpen && (
                <BottomSheet
                    title="탈퇴사유"
                    closeModal={closeBottomSheet}
                    contents={popContents}
                    isOpen={isBottomOpen}
                    buttonText="확인"
                    onBtnClick={selectReason}
                />
            )}

            {isAlertOpen && (
                <Modal 
                    title="회원탈퇴"
                    content="정말 탈퇴하시겠습니까?"
                    modalText1="취소"
                    modalText2="탈퇴하기"
                    isOpen={isAlertOpen}
                    btnClick01={closeAlert}
                    // btnClick02={}
                />
            )}
            
        </div>
    );
}

export default Withdrawal;
