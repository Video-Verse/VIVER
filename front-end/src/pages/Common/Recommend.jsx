import $ from 'jquery';
import React, { useEffect, useRef, useState } from "react";
import Slider from "react-slick";
import DockBar from "../../components/DockBar/DockBar";
import Header from "../../components/Header/Header";
import Card from "../Main/Card";
import Filter from "../../components/bottomsheet/fillter";

const Recommend = () => {
    const [activeTab, setActiveTab] = useState(0);
    const tabs = ['영화', '드라마', '뮤지컬'];
    const sliderRef = useRef(null);


    const handleTabClick = (index) => { 
        setActiveTab(index);
        sliderRef.current.slickGoTo(index);
    };

    useEffect(() => {
        $("#btn-back").css('display', 'none');
        $("#title").css('display', 'none');
        
        const setTabContentHeight = () => {
            const tabContent = document.querySelector('.tab-content');
            const screenHeight = window.innerHeight;
            tabContent.style.height = `${screenHeight}px`;
        };
    
        setTabContentHeight();
        window.addEventListener('resize', setTabContentHeight);
    
        return () => {
            window.removeEventListener('resize', setTabContentHeight);
        };
    }, []);


    const slideSettings = {
        dots: false,
        arrows: false,
        infinite: true, // 무한 스크롤
        speed: 700, // 전환 속도 (milliseconds)
        slidesToShow: 1,
        slidesToScroll: 1,
        initialSlide: activeTab,
        cssEase: 'ease-out', // 자연스러운 전환을 위한 ease 설정
        swipeToSlide: true, // 슬라이드를 바로 이동할 수 있도록 설정
        swipe: true, // 터치 또는 스와이프 지원
        afterChange: handleTabClick, // 슬라이드가 바뀌었을 때 탭을 갱신
    };

    const LockerData = {
        movies: [
            { id: 1, title: '영화 1', category: 'movies' },
            { id: 2, title: '영화 2', category: 'movies' },
            { id: 3, title: '영화 3', category: 'movies' },
            { id: 4, title: '영화 4', category: 'movies' },
            { id: 5, title: '영화 5', category: 'movies' },
        ],
        tv: [
            { id: 6, title: '드라마 1', category: 'tv' },
            { id: 7, title: '드라마 2', category: 'tv' },
            { id: 8, title: '드라마 3', category: 'tv' },
        ],
        musical: [
            { id: 9, title: '뮤지컬 1', category: 'musical' },
            { id: 10, title: '뮤지컬 2', category: 'musical' },
            { id: 11, title: '뮤지컬 3', category: 'musical' },
        ],
    };

    const getLockerDataByTab = () => {
        if (activeTab === 0) return LockerData.movies;
        if (activeTab === 1) return LockerData.tv;
        if (activeTab === 2) return LockerData.musical;
    };

    const [bottomSheetVisible, setBottomSheetVisible] = useState(false);
    const toggleBottomSheet = () => setBottomSheetVisible(!bottomSheetVisible);
    const openFilter = () => setBottomSheetVisible(true);
    const closeFilter = () => setBottomSheetVisible(false);

    return (
        <div>
            <Header />
            <div>
                <div className="tabs-header">
                    {tabs.map((tab, index) => (
                        <TabButton
                            key={index}
                            title={tab}
                            active={activeTab === index}
                            onClick={() => handleTabClick(index)}
                        />
                    ))}
                </div>
                <Slider {...slideSettings} afterChange={handleTabClick} ref={(ref) => (sliderRef.current = ref)}>
                    {tabs.map((tab, index) => (
                        <div key={index} className="tab-content">
                            {["인기작", "최신작", "추천작"].map((sectionTitle) => (
                                <div key={sectionTitle} style={{ marginBottom: '30px' }}>
                                    <h3 style={{ marginBottom: '10px' }}>{sectionTitle}</h3>

                                    <Card title={tab} data={getLockerDataByTab()} />

                                </div>
                            ))}
                        </div>
                    ))}
                </Slider>
            </div>
            
            <Filter
                visible={bottomSheetVisible}
                title="상세 필터"
                closeModal={closeFilter}
            />
            <DockBar />
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

export default Recommend;
