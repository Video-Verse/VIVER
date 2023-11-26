import React, { useState, useEffect, useRef } from "react";
import Slider from "react-slick";

import "../Common/Home.css";
import Header from "../../components/Header/Header";
import Card from "../Main/Card";
import { useParams } from 'react-router-dom';

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
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        initialSlide: activeTab
    };
    
    //가상데이터
    const LockerData = {
        movies: [
            { id: 1, title: '영화 1', category: 'movies' },
            { id: 2, title: '영화 2', category: 'movies' },
            { id: 2, title: '영화 4', category: 'movies' },
            { id: 2, title: '영화 7', category: 'movies' },
            { id: 2, title: '영화 4', category: 'movies' },
        ],
        tv: [
            { id: 3, title: 'tv 1', category: 'tv' },
            { id: 4, title: 'tv 2', category: 'tv' },
        ],
        musical: [
            { id: 5, title: 'musical 1', category: 'musical' },
            { id: 6, title: 'musical 2', category: 'musical' },
        ],
    };
    console.log(LockerData);

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
                            {<Card title="영화" data={LockerData.movies} />}
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




