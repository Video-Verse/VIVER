import React from "react";
import { useLocation } from "react-router";
import { useNavigate } from 'react-router-dom';
import completeImg from '../../assets/images/img_cong.png';
import CommonBtn from "../../components/button/button";

const Complete = () => {
	const nickName = localStorage.getItem("nickName");
	const navigate = useNavigate();
    const handleStartClick = () => {
        navigate('/home'); 
    };
    
    return (
        <div>
            <div className="complete">
                <div className="img-box">
                    <img src={completeImg} alt="환영합니다" className="img-complete"/>
                    <h2 className="content-title">
                        { nickName } 님 환영합니다!
                    </h2>
                    <CommonBtn buttonText="시작하기" onClick={handleStartClick}/>
                </div>
               
            </div>
           
        </div>
    )
};

export default Complete;



