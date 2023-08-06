import React from "react";
import Header from "../../components/Header/Header";
import './Join.css';
import CommonInp from "../../components/input/input";
import CommonBtn from "../../components/button/button";

const Join = () => {
    return (
        <div className="">
            <Header />
        
            <div className="wrap">
                <div className="content">
                    <h3 className="content-title">
                        비버에서 사용할<br/>
                        닉네임을 입력해 주세요
                    </h3>
                    <p className="sub-txt">* 2 ~ 10자 이내로 입력해주세요.</p>
                    <CommonInp/>
                </div>
                <CommonBtn />
            </div>
        </div>
    )
}

export default Join;