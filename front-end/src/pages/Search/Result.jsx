import React, { useState } from "react";

import './Search.css';
import Header from './../../components/Header/Header';
import SearchInp from './SearchInp';

import poster from '../../assets/images/img_sample.png';
import poster2 from '../../assets/images/img_sample2.png';


const Result = () => {
    const [searchCount, setSearchCount] = useState(0); //검색결과 카운트
    
    const handleSearch = (searchItems) => {
        console.log('search :', searchItems);
        
        setSearchCount(11); //실제 결과 갯수 업뎃
    };
    
    const [activeDibs, setActiveDibs] = useState(null);
    const toggleDibs = (itemId) => {
        setActiveDibs((prevDibs) => {
            if (prevDibs.includes(itemId)) {
                return prevDibs.filter((id) => id !== itemId);
            } else {
                return [...prevDibs, itemId];
           }
       })
    }

    return (
        <div>
            <Header />
            <div className="wrap">
                <SearchInp onSearch={handleSearch} />
                <div className="content">
                    <h3 className="content-title">
                        검색결과 ({searchCount})
                    </h3>
                    
                    <div className="view-area">
                        {/* 내보관함에서 불러옴 - 저장기능 없음 */}
                        <h3 className="view-count">내 작품 <span className="num">[N]</span></h3>
                        <ul className="list-wrap">
                            <li>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">엘리멘탈</span>
                                </a>
                            </li>
                            <li>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">세인트 세이야 더 비기닝 블라블라</span>
                                </a>
                            </li>
                            <li>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">응답하라 1993</span>
                                </a>
                            </li>
                            <li>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">title</span>
                                </a>
                            </li>
                            <li>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">title</span>
                                </a>
                            </li>
                            <li className="more">
                                <a href="#none">
                                   + 더보기
                                </a>
                            </li>
                        </ul>
                    </div>
                    
                    <div className="view-area">
                        {/* 영화 */}
                        <h3 className="view-count">영화 <span className="num">[N]</span></h3>
                        <ul className="list-wrap">
                            <li>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster2} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">엘리멘탈</span>
                                </a>
                                <button className={`btn-dibs ${activeDibs === 'btn-dibs' ? 'btn-dibs-after' : 'btn-dibs'}`}
                                    onClick={() => toggleDibs()}
                                ><span className="blind">보관함에 저장하기</span></button>
                            </li>
                            <li>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">세인트 세이야 더 비기닝 블라블라</span>
                                </a>
                            </li>
                            <li>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster2} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">응답하라 1993</span>
                                </a>
                            </li>
                            <li>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">title</span>
                                </a>
                            </li>
                            <li>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">title</span>
                                </a>
                            </li>
                            <li className="more">
                                <a href="#none">
                                   + 더보기
                                </a>
                            </li>
                        </ul>
                    </div>
                    
                    {/* <p className="nodata">일치하는 검색결과가 없습니다.</p> */}
                </div>
            </div>
        </div>
    )
}

export default Result;