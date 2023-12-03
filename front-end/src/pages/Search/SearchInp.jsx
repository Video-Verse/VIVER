import React, { useState, useRef, useEffect } from "react";
import { useNavigate  } from 'react-router-dom';
import './Search.css';
import $ from 'jquery';
import axios from 'axios';

const SearchInp = ({ onSearch }) => {
	const inputRef = useRef();
	
	useEffect(() => {
		inputRef.current.focus();
		
	})
    const [searchItems, setSearchItems] = useState('');
    const navigate = useNavigate ();

    const handleChange = (event) => {
        setSearchItems(event.target.value);
    };

    const handleClear = () => {
        //검색어 삭제
        setSearchItems('');
    }

    const search = (e) => {
		const keyword = $("#searchKeyword").val();
		const type = $('input[name=searchCategory]:checked').val();
		const param = {
			"keyword" : keyword
			, "type" : type
		}
		
		axios.post("/search", param)
		.then(response => {
			console.log(response)
		})
		/*fetch("/search", param)
		.then(response => {
			console.log(response)
		}).then(message => {
			console.log(message)
		})*/
        //검색기능 수행
        /*if (onSearch) {
            if (searchItems === 'nodata') {
                navigate.push('/nodata');
            } else {
                onSearch(searchItems);
            }
        }*/
    };

    return (
        <div className="input-box search-inp">
            <button type="button" className="btn-search" onClick={search}>
            	<span className="blind">검색</span>
        	</button>
            <input type="text" id="searchKeyword" ref={inputRef} className="input" placeholder="검색어를 입력하세요" value={searchItems} onChange={handleChange}/>
            {searchItems && (
                <button type="button" className="btn-del" onClick={handleClear}><span className="blind">삭제</span></button>
            )}
            
        </div>
    )
}

export default SearchInp;














