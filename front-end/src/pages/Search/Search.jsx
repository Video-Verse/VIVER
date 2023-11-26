import React from "react";

import Header from "../../components/Header/Header";
import './search.css';
import SearchInp from "./SearchInp";

const Search = () => {
    const handleSearch = (searchItems) => {
        console.log('search :', searchItems);
    };

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
                            <input type="radio" id="categoryAll" name="searchCategory" />
                            <label htmlFor="categoryAll">전체</label>
                        </div>
                        <div className="radio-box">
                            <input type="radio" id="categoryMv" name="searchCategory" />
                            <label htmlFor="categoryMv">영화</label>
                        </div>
                        <div className="radio-box">
                            <input type="radio" id="categoryTv" name="searchCategory" />
                            <label htmlFor="categoryTv">TV</label>
                        </div>
                        <div className="radio-box">
                            <input type="radio" id="categoryMs" name="searchCategory" />
                            <label htmlFor="categoryMs">뮤지컬</label>
                        </div>
                    </div>
                    
                    <SearchInp onSearch={handleSearch} />
                </div>

            </div>
        </div>
  );
};
export default Search;
