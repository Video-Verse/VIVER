import React, { useState } from "react";
import './DockBar.css';
import { Link } from "react-router-dom";

const DockBar = () => {
    const [activeItem, setActiveItem] = useState(null); //활성화된 항목 찾기 및 class 추가 스타일 적용

    const handleItemClick = (item) => {
        setActiveItem(item);
    };

//버튼 활성화 하기!!!
    return (
        <div className="dock-bar">
            <div className="dock-group">
                <ul>
                    <li className={`dock-item ${activeItem === 'home' ? 'active' : ''}`}
                        onClick={() => handleItemClick('home')} >
                        <Link to="/home" className="dock-ic home"><span className="blind">홈</span></Link>
                    </li>
                    <li className={`dock-item ${activeItem === 'recommend' ? 'active' : ''}`}
                        onClick={() => handleItemClick('recommend')} >
                        <Link to="/recommend" className="dock-ic recommend"><span className="blind">추천작</span></Link>
                    </li>
                    <li className={`dock-item ${activeItem === 'locker' ? 'active' : ''}`}
                        onClick={() => handleItemClick('locker')} >
                        <Link to="/locker" className="dock-ic locker"><span className="blind">보관함</span></Link>
                    </li>
                    <li className={`dock-item ${activeItem === 'mypage' ? 'active' : ''}`}
                        onClick={() => handleItemClick('mypage')} >
                        <Link to="/mypage" className="dock-ic mypage"><span className="blind">마이페이지</span></Link>
                    </li>
                </ul>
                <a href="#none" className="btn-add"><span className="blind">추가하기</span></a>
            </div>
        </div>
    )
    
};

export default DockBar;