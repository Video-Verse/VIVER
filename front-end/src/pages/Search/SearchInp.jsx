import React, { useState } from "react";
import { useNavigate  } from 'react-router-dom';
import './Search.css';

const SearchInp = ({ onSearch }) => {
    
    const [searchItems, setSearchItems] = useState('');
    const navigate = useNavigate ();

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
            if (searchItems === 'nodata') {
                navigate.push('/nodata');
            } else {
                onSearch(searchItems);
            }
        }
        
        console.log("dddd")
    };

    return (
        <div className="input-box search-inp">
            <button type="button" className="btn-search" onClick={handleSearch}><span className="blind">검색</span></button>
            <input type="text" className="input" placeholder="검색어를 입력하세요" value={searchItems} onChange={handleChange}/>
            {searchItems && (
                <button type="button" className="btn-del" onClick={handleClear}><span className="blind">삭제</span></button>
            )}
            
        </div>
    )
}

export default SearchInp;














