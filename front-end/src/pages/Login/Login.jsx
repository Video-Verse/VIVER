import React from "react";
import logo from '../../assets/images/logo.png';
import './login.css';

// import CommonBtn from './components/button/button';

const Login = () => {
  return(
    <div className="wrap">
        <div className="content">
            <div className="img-box">
                <h3 className="login-title">나의 취향 저장소</h3>
                <img src={logo} className="logo" alt="logo" />
            </div>
            <div className="btn-group">
                <button type="button" className="btn btn-kakao"><span>카카오로 로그인</span></button>
                <button type="button" className="btn btn-naver"><span>네이버로 로그인</span></button>
            </div>
        </div>
    </div>
  );
};

export default Login;




