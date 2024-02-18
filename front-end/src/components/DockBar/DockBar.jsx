import React, { useState } from "react";
import './DockBar.css';
import { Link, useLocation } from "react-router-dom";

const DockBar = () => {
    const handleItemClick = (item) => {
        setActiveItem(item);
    };

    const location = useLocation(); // 현재 경로 가져오기
    const [activeItem, setActiveItem] = useState(null);

    // 경로를 기반으로 활성 항목 설정 , 경로가 변경될 때마다 실행
    React.useEffect(() => {
        switch (location.pathname) {
            case '/home':
                setActiveItem('home');
                break;
            case '/recommend':
                setActiveItem('recommend');
                break;
            case '/locker':
                setActiveItem('locker');
                break;
            case '/mypage':
                setActiveItem('mypage');
                break;
            default:
                setActiveItem(null);
        }
    }, [location.pathname]); 
    
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