import React from "react";
import { useNavigate  } from 'react-router-dom';
import './Header.css';



const Header = () => {
	const navigate = useNavigate ();
	const onClickBtn = () => {
    navigate(-1); // 바로 이전 페이지로 이동, '/main' 등 직접 지정도 당연히 가능
  };
  
    return(
        <header className="header">
            <button type="button" className="btn-back" id="btn-back" onClick={onClickBtn}><span className="blind">뒤로가기</span></button>
            <h1 className="header-logo" id="logo"><span className="blind">비버</span></h1>
            <h3 className="title" id="title">Title</h3>
            <button type="button" className="btn-search" id="btn-search"><span className="blind">검색</span></button>
        </header>
    );
};

export default Header;