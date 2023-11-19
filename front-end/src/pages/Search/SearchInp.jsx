import React, { useState } from "react";
// import { useHistory } from 'react-router-dom';
import './Search.css';

const SearchInp = ({ onSearch }) => {
    
    const [searchItems, setSearchItems] = useState('');
    // const history = useHistory();

    const handleChange = (event) => {
        setSearchItems(event.target.value);
    };

    const handleClear = () => {
        //검색어 삭제
        setSearchItems('');
    }

    const handleSearch = (searchItems) => {
        //검색기능 수행
        if (onSearch) {
            onSearch(searchItems);
            // if (searchItems === 'nodata') {
            //     history.push('/nodata');
            // } else {
            //     onSearch(searchItems);
            // }

        }
    };

    return (
        <div className="input-box">
            <button type="button" className="btn-search" onClick={handleSearch}><span className="blind">검색</span></button>
        <   input type="text" className="input" placeholder="검색어를 입력하세요" value={searchItems} onChange={handleChange}/>
            {searchItems && (
                <button type="button" className="btn-del" onClick={handleClear}><span className="blind">삭제</span></button>
        )}
            
        </div>
    )
}

export default SearchInp;














