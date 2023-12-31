import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from 'react-router-dom';
import $ from 'jquery';
import axios from 'axios';

import './Search.css';
import Header from './../../components/Header/Header';
import SearchInp from './SearchInp';

import poster from '../../assets/images/img_sample.png';
import DockBar from "../../components/DockBar/DockBar";

const Result = () => {
    const location = useLocation();
    const searchKeyword = location.state.keyword;
    var data = location.state.data;

    console.log(data)
    var bookmark = [];
    var movie = [];
    var tv = [];
    var musical = [];

    useEffect(() => {
        $("#title").text("검색결과");
        $("#btn-search").css('visibility', 'hidden');
        $("#searchKeyword").val(searchKeyword);
        makeCard(data);
    })

    function makeCard(data) {
        bookmark = data.bookmark;
        movie = data.movie;
        tv = data.tv;
        musical = data.musical;

        var movieCnt = movie.length;
        var tvCnt = tv.length;
        var musicalCnt = musical.length;
        var bookmarkCnt = bookmark.length;

        var totalCnt = movieCnt + tvCnt + musicalCnt + bookmarkCnt;
        $("#bookmarkCnt").text("[" + bookmarkCnt + "]");
        $("#movieCnt").text("[" + movieCnt + "]");
        $("#tvCnt").text("[" + tvCnt + "]");
        $("#musicalCnt").text("[" + musicalCnt + "]");

        $("#totalCnt").text("(" + totalCnt + ")");
    }

    /* const [searchCount, setSearchCount] = useState(totalCnt); //검색결과 카운트
     
     const handleSearch = (searchItems) => {
         console.log('search :', searchItems);
         
         setSearchCount(11); //실제 결과 갯수 업뎃
     };*/

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
                <SearchInp />
                <div className="content">
                    <h3 className="content-title">
                        검색결과 <span id="totalCnt">[N]</span>
                    </h3>

                    <div className="view-area">
                        {/* 내보관함에서 불러옴 - 저장기능 없음 */}
                        <h3 className="view-count">내 작품 <span className="num" id="bookmarkCnt">[N]</span></h3>
                        <ul className="list-wrap">
                            {data.bookmark.map((item) => (

                                <li key={item.bm_id}>
                                    {/* Movie 정보 */}
                                    {item.movie_id && (
                                        <a href="#none" id={item.movie_id}>
                                            <div className="img-box">
                                                <img src={poster} alt="poster" className="poster" />
                                            </div>
                                            <span className="name">{item.movie_title}</span>
                                        </a>
                                    )}

                                    {/* TV 정보 */}
                                    {item.tv_id && (
                                        <a href="#none" id={item.tv_id}>
                                            <div className="img-box">
                                                <img src={poster} alt="poster" className="poster" />
                                            </div>
                                            <span className="name">{item.tv_title}</span>
                                        </a>
                                    )}

                                    {/* Musical 정보 */}
                                    {item.musical_id && (
                                        <a href="#none" id={item.musical_id}>
                                            <div className="img-box">
                                                <img src={poster} alt="poster" className="poster" />
                                            </div>
                                            <span className="name">{item.musical_title}</span>
                                        </a>
                                    )}
                                </li>
                            ))}

                            {/*더보기 */}
                            {data.bookmark.length > 5 && (
                                <li className="more">
                                    <a href="#none">+ 더보기</a>
                                </li>
                            )}
                        </ul>
                    </div>

                    <div className="view-area">
                        {/* 영화 */}
                        <h3 className="view-count">영화 <span className="num" id="movieCnt">[N]</span></h3>
                        <ul className="list-wrap">
                            {data.movie.length > 0 ? (
                                data.movie.slice(0, 5).map((singleMovie) => (
                                    <Card key={singleMovie.db_id} id={singleMovie.id} title={singleMovie.title} name={singleMovie.org_title} posterPath={singleMovie.poster} />
                                ))
                            ) : (
                                <p className="nodata">일치하는 검색결과가 없습니다.</p>
                            )}

                            {/*더보기 */}
                            {data.movie.length > 5 && (
                                <li className="more">
                                    <a href="#none">+ 더보기</a>
                                </li>
                            )}
                        </ul>
                    </div>

                    <div className="view-area">
                        {/* TV */}
                        <h3 className="view-count">TV <span className="num" id="tvCnt">[N]</span></h3>
                        <ul className="list-wrap">
                            {data.tv.length > 0 ? (
                                data.tv.slice(0, 5).map((singleTv) => (
                                    <Card key={singleTv.db_id} id={singleTv.id} title={singleTv.title} posterPath={singleTv.poster} />
                                ))
                            ) : (
                                <p className="nodata">일치하는 검색결과가 없습니다.</p>
                            )}

                            {/*더보기 */}
                            {data.tv.length > 5 && (
                                <li className="more">
                                    <a href="#none">
                                        + 더보기
                                    </a>
                                </li>
                            )}
                        </ul>
                    </div>

                    <div className="view-area">
                        {/* 뮤지컬 */}
                        <h3 className="view-count">뮤지컬 <span className="num" id="musicalCnt">[N]</span></h3>
                        <ul className="list-wrap">
                            {data.musical.length > 0 ? (
                                data.musical.slice(0, 5).map((singleMusical) => (
                                    <Card key={singleMusical.db_id} id={singleMusical.id} title={singleMusical.title} posterPath={singleMusical.poster} />
                                ))
                            ) : (
                                <p className="nodata">일치하는 검색결과가 없습니다.</p>
                            )}

                            {/*더보기 */}
                            {data.musical.length > 5 && (
                                <li className="more">
                                    <a href="#none">
                                        + 더보기
                                    </a>
                                </li>
                            )}
                        </ul>
                    </div>

                    {/* <p className="nodata">일치하는 검색결과가 없습니다.</p>  */}
                </div>
            </div>
            <DockBar/>
        </div>
    )
}

function Card(props) {
    console.log(props)
    return (
        <li key={props.id}>
            <a href="#none" id={props.db_id}>
                <div className="img-box">
                    <img src={poster} alt="poster" className="poster" />
                    {/*<img src={"https://image.tmdb.org/t/p/original"+props.poster_path} alt="poster" className="poster"/>*/}
                </div>
                <span className="name">{props.title}</span>
            </a>
            <button><span className="blind">보관함에 저장하기</span></button>
            {/*<button className={`btn-dibs ${activeDibs === 'btn-dibs' ? 'btn-dibs-after' : 'btn-dibs'}`}
                onClick={() => toggleDibs()}
            ><span className="blind">보관함에 저장하기</span>
            </button>*/}
        </li>
    )
}

export default Result;