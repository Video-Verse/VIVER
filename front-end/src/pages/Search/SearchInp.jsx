import React, { useState, useRef, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import './Search.css';
import $ from 'jquery';
import axios from 'axios';

const SearchInp = ({ onSearch }) => {
	const inputRef = useRef();
	const navigate = useNavigate();

	useEffect(() => {
		inputRef.current.focus();

	})
	const [searchItems, setSearchItems] = useState('');

	const handleChange = (event) => {
		setSearchItems(event.target.value);
	};

	const handleClear = () => {
		//검색어 삭제
		setSearchItems('');
	}

	/*$("#searchKeyword").keypress(function(e){
		//검색어 입력 후 엔터키 입력하면 조회버튼 클릭
		if(e.keyCode && e.keyCode == 13){
			search();
		}
		
	});*/
	
	const search = (e) => {
		const keyword = $("#searchKeyword").val();
		const type = $('input[name=searchCategory]:checked').val();
		const param = {
			"keyword": keyword
			, "type": type
		}

		axios.post("/search", param)
			.then(response => {
				const data = response.data;
				data['keyword']=keyword;
				data['type']=type;
				navigate("/result", {state:data});
			})

	};

	return (
		<div className="input-box search-inp">
			<button type="button" className="btn-search" onClick={search}>
				<span className="blind">검색</span>
			</button>
			<input type="text" id="searchKeyword" ref={inputRef} className="input" placeholder="검색어를 입력하세요" value={searchItems} onChange={handleChange} />
			{searchItems && (
				<button type="button" className="btn-del" onClick={handleClear}><span className="blind">삭제</span></button>
			)}

		</div>
	)
}

export default SearchInp;














