import React from "react";
import { useParams } from "react-router-dom";

import Header from './../../components/Header/Header';
import completeImg from '../../assets/images/img_cong.png';


const Complete = () => {
    const { nickName } = useParams();
    return (
        <div>
            <Header />
            <div className="complete">
                <div className="img-box">
                    <img src={completeImg} alt="환영합니다" className="img-complete"/>
                    <h2 className="content-title">
                        [{ nickName }] 님 환영합니다!
                    </h2>
                </div>
               
            </div>
           
        </div>
    )
};

export default Complete;



