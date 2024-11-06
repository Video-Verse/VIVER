import React, { useState } from "react";
import Header from "../../components/Header/Header";
import SearchInp from "./SearchInp";

const Nodata = () => {
    const [searchCount, setSearchCount] = useState(0); //검색결과 카운트
    
    const handleSearch = (searchItems) => {
        console.log('search :', searchItems);
        
        setSearchCount(11); //실제 결과 갯수 업뎃
    };

    return (
        <div>
            <Header />
            <div className="wrap">
                <SearchInp onSearch={handleSearch} />
                <div className="content nodata">
                    <h3 className="content-title">
                        검색결과 ({searchCount})
                    </h3>
                    <p className="nodata">일치하는 검색결과가 없습니다.</p>
                </div>
            </div>
            
        </div>
    )
};

export default Nodata;