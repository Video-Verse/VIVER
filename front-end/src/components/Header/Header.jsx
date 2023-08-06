import React from "react";
import './Header.css';



const Header = () => {
    return(
        <header className="header">
            <button type="button" className="btn-back"><span className="blind">뒤로가기</span></button>
            {/* <h1 className="header-logo"><span className="blind">비버</span></h1> */}
            <h3 className="title">Title</h3>
            <button type="button" className="btn-search"><span className="blind">검색</span></button>
        </header>
    );
};

export default Header;