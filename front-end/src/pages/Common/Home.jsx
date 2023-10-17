import React, { useState, useEffect, useRef } from "react";
import Slider from "react-slick";

import "../Common/Home.css";
import Header from "../../components/Header/Header";


const MainSlider = () => {
    const [activeTab, setActiveTab] = useState(0);
    const tabs = ['영화', '드라마', '뮤지컬']

    const handleTabClick = (index) => { 
        setActiveTab(index); //탭 클릭시 활성화된 슬라이드 연결
        sliderRef.current.slickGoTo(index) //슬라이드 이동시 활성화 탭 연결
    };

    const sliderRef = useRef(null);

    //탭영역 높이값 설정
    useEffect(() => {
        // 화면 높이를 얻어오는 함수
        const setTabContentHeight = () => {
          const tabContent = document.querySelector('.tab-content');
          const screenHeight = window.innerHeight;
          tabContent.style.height = `${screenHeight}px`;
        };
    
        setTabContentHeight();
    
        // 창의 크기가 변경될 때도 높이를 업데이트
        window.addEventListener('resize', setTabContentHeight);
    
        return () => {
          window.removeEventListener('resize', setTabContentHeight);
        };
    }, []);

    const slideSettings = { //슬라이드 옵션
        dots: false,
        arrows: false,
        Infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        initialSlide: activeTab
    };

    return (
        <div>
            <Header />
            <div>
                <div className="tabs-header">
                    {tabs.map((tab, index) => (
                        <TabButton
                            key={index}
                            title={tab}
                            active={activeTab === index} //활성화된 슬라이드랑 연결
                            onClick={() => handleTabClick(index)}
                        />
                    ))}
                </div>
                <Slider {...slideSettings} afterChange={handleTabClick} ref={(ref) => (sliderRef.current = ref)}>
                    {tabs.map((tab, index) => (
                        <div key={index} className="tab-content">
                            <div className="filter-area">
                                <p className="total">총 <span className="count">[N]</span>건</p>
                                <a href="#none" className="filter"><span className="blind">필터</span></a>
                            </div>
                            {'$(tab} content'}
                        </div>
                    ))}
                </Slider>
            </div>
        </div>
      
    );
};

const TabButton = ({ title, active, onClick }) => {
    const tabClass = `tab-title ${active ? 'active' : ''}`;

    return (
        <button
            className={tabClass}
            onClick={onClick}
            >
            {title}
        </button>
  );
};

export default MainSlider




