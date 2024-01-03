import React, { useState, useEffect } from "react";
import { useLocation } from 'react-router-dom';
import $ from 'jquery';

import './Search.css';
import Header from './../../components/Header/Header';
import SearchInp from './SearchInp';

import poster from '../../assets/images/img_sample.png';
import DockBar from "../../components/DockBar/DockBar";
import ToastPop from "../../components/toastpop/ToastPop";
import Bookmark from "../Mypage/Bookmark";

const Result = () => {
   
    const location = useLocation();
    const searchKeyword = location.state.keyword;
    // const searchKeyword = location?.state?.keyword || '';
    var data = location.state.data;

	const allId = $("#categoryAll").val();
	const movieId = $("#categoryMv").val();
	const tvId = $("#categoryTv").val();
	const musicalId = $("#categoryMs").val();
	
    var bookmark = [];
    var movie = [];
    var tv = [];
    var musical = [];

    useEffect(() => {
        $("#title").text("검색");
        $("#logo").css('display', 'none');
        $("#btn-search").css('visibility', 'hidden');
        $("#searchKeyword").val(searchKeyword);
        makeCard(data);
    })

    function makeCard(data) {
        bookmark = data.bookmark || [];
        movie = data.movie || [];
        tv = data.tv || [];
        musical = data.musical || [];

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

    //북마크
    const [isbkMarkClicked, setIsbkMarkClicked] = useState(false);

    //각 리스트별 카드 생성
    function Card(props) {
        return (
            <li key={props.id} id={props.id}>
                <a href="#none">
                    <div className="img-box">
                        <img src={poster} alt="poster" className="poster" />
                        {/*<img src={"https://image.tmdb.org/t/p/original"+props.poster_path} alt="poster" className="poster"/>*/}
                    </div>
                    <span className="name">{props.title}</span>
                </a>
                <button className={`btn-bookmark ${isbkMarkClicked ? 'btn-bookmark-after' : ''}`}
                    onClick={() => {
                        addBookmark(props.id, props.type);
                        setIsbkMarkClicked(true);
                    }}>
                
                    <span className="blind">보관함에 저장하기</span>
                </button>
            </li>
        )
    }
   
    //toast popup
    const [showMorePopup, setShowMorePopup] = useState(false); 
    
    //보관함 추가하기
    const addBookmark = (id, type) => {
        //1. 해당 아이디로 디비 찾기 
        console.log(id, type);
        
        //2. userid도 받아서 보관함 db에 넣기
        
        //팝업 띄우기 + active
        setShowMorePopup(true);
        
    };
    
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
                        	{data.bookmark.length > 0 ? (
                               data.bookmark.slice(0, 5).map((item) => (
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
                                </li>)
                            )) : (
								<p className="nodata">일치하는 검색결과가 없습니다.</p> 
								)}

                            {/*더보기 */}
                            {data.bookmark.length > 5 && (
                                <li className="more">
                                    <a href="#none">+ 더보기</a>
                                </li>
                            )}
                        </ul>
                    </div>

					{(data.type === allId || data.type === movieId) && (
                    <div className="view-area" id="mvArea">
                        {/* 영화 */}
                        <h3 className="view-count">영화 <span className="num" id="movieCnt">[N]</span></h3>
                        <ul className="list-wrap">
                            {data.movie.length > 0 ? (
                                data.movie.slice(0, 5).map((singleMovie) => (
                                    <Card key={singleMovie.db_id} id={singleMovie.db_id} type={data.type} title={singleMovie.title} name={singleMovie.org_title} posterPath={singleMovie.poster} />
                                ))
                            ) : (
                                <p className="nodata">일치하는 검색결과가 없습니다.</p>
                            )}

                            {/*더보기 */}
                            {data.movie.length > 5 && (
                                <li className="more">
                                    <a href="#none" >+ 더보기</a>
                                </li>
                            )}
                            <button className="test" onClick={() => setShowMorePopup(true)}>팝업테스트</button>
                        </ul>
                    </div> )}

					{(data.type === allId || data.type === tvId) && (
                    <div className="view-area" id="tvArea">
                        {/* TV */}
                        <h3 className="view-count">TV <span className="num" id="tvCnt">[N]</span></h3>
                        <ul className="list-wrap">
                            {data.tv.length > 0 ? (
                                data.tv.slice(0, 5).map((singleTv) => (
                                    <Card key={singleTv.db_id} id={singleTv.id} type={data.type} title={singleTv.title} posterPath={singleTv.poster} />
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
                    </div>)}

					{(data.type === allId || data.type === musicalId) && (
                    <div className="view-area" id="musicalArea">
                        {/* 뮤지컬 */}
                        <h3 className="view-count">뮤지컬 <span className="num" id="musicalCnt">[N]</span></h3>
                        <ul className="list-wrap">
                            {data.musical.length > 0 ? (
                                data.musical.slice(0, 5).map((singleMusical) => (
                                    <Card key={singleMusical.db_id} id={singleMusical.id} type={data.type} title={singleMusical.title} posterPath={singleMusical.poster} />
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
                    </div>)}

                    {/* <p className="nodata">일치하는 검색결과가 없습니다.</p>  */}
                 
                </div>
            </div>
            {/*toast popup */}
            {showMorePopup && (
                <ToastPop
                message="보관함에 저장되었습니다."
                isVisible={showMorePopup}
                onClose={() => setShowMorePopup(false)}
                closeButtonText="취소하기"
                />
            )}
           
            <DockBar/>
        </div>
    )
}

export default Result;