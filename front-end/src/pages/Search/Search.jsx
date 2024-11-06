import React, {useEffect, useRef, useNavigate } from "react";
import $ from 'jquery';

import Header from "../../components/Header/Header";
import SearchInp from "./SearchInp";
import DockBar from "../../components/DockBar/DockBar";

const Search = () => {
	
	useEffect(() => {
		$("#categoryAll").prop("checked", true);
        $("#title").text("검색");
        $("#logo").css('display', 'none');
		$("#btn-search").css('visibility', 'hidden');
	})
	
	
    return (
            
        <div>
            <Header />
            <div className="wrap">
                <div className="content">
                    <h3 className="content-title">
                        제목, 감독, 배우 등<br />
                        자유롭게 검색해보세요.
                    </h3>
                    <p className="sub-txt">* 카테고리는 중복 선택되지 않습니다.</p>
                    
                    <div className="category">
                        <div className="radio-box">
                            <input type="radio" id="categoryAll" name="searchCategory" value="ALL"/>
                            <label htmlFor="categoryAll">전체</label>
                        </div>
                        <div className="radio-box">
                            <input type="radio" id="categoryMv" name="searchCategory" value="MV"/>
                            <label htmlFor="categoryMv">영화</label>
                        </div>
                        <div className="radio-box">
                            <input type="radio" id="categoryTv" name="searchCategory" value="TV"/>
                            <label htmlFor="categoryTv">TV</label>
                        </div>
                        <div className="radio-box">
                            <input type="radio" id="categoryMs" name="searchCategory" value="MS"/>
                            <label htmlFor="categoryMs">뮤지컬</label>
                        </div>
                    </div>
                    
                    <SearchInp />
                </div>

            </div>
            <DockBar/>
        </div>
  );
};
export default Search;
