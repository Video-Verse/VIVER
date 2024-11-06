import React, { useState } from "react";
import icClose from '../../assets/images/ic_close_white.png';
import PropTypes from 'prop-types';
import CommonBtn from "../button/button";

function Filter({ title, visible, closeModal }) {
   
    //카드,리스트 선택
    const [selectedOption, setSelectedOption] = useState("카드형");

    const selectOption = (option) => {
        setSelectedOption(option);
    };
        
    //정렬 조건 설정
    const [sortOption, setSortOption] = useState("");

    const sortOptions = (option) => {
        setSortOption(option);
    };

    
    return (
    <div className={`bottom-sheet ${visible ? 'open' : ''}`}>
        <div className="modal-background"/>
        <div className="bs">
            <div className="bs-header">
                    <img src={icClose} alt="닫기" onClick={closeModal} />
                    <button className="btn-reset">초기화</button>
            </div>
            <div className="bs-content">
                <h1 className="title">{title}</h1>
                <div className="option-box">
                    <span className="sub-tit">보기</span>
                    <ul className="option">
                        <li className={`option-item ${selectedOption === "카드형" ? "on" : ""}`}
                            onClick={() => selectOption("카드형")}
                        >카드형</li>
                        <li className={`option-item ${selectedOption === "리스트형" ? "on" : ""}`}
                            onClick={() => selectOption("리스트형")}
                        >리스트형</li>
                    </ul>
                </div>
                    
                <div className="option-box">
                    <span className="sub-tit">정렬</span>
                    <div className="radio-box">
                        <label htmlFor="sortOption01" className={sortOption === "최신" ? "checked" : ""}>최신</label>
                            <input type="radio" className="radio" id="sortOption01" name="sortOption" checked={sortOption === "최신"} onChange={() => sortOptions("최신")} />
                    </div>  
                    <div className="radio-box">
                        <label htmlFor="sortOption02" className={sortOption === "별점" ? "checked" : ""}>별점</label>
                        <input type="radio" className="radio" id="sortOption02" name="sortOption" checked={sortOption === "별점"} onChange={() => sortOptions("별점")} />
                    </div> 
                    <div className="radio-box">
                        <label htmlFor="sortOption03" className={sortOption === "관람일자" ? "checked" : ""}>관람일자</label>
                        <input type="radio" className="radio" id="sortOption03" name="sortOption"  checked={sortOption === "관람일자"} onChange={() => sortOptions("관람일자")}></input>
                    </div> 
                </div>    
            </div>
            <CommonBtn
                buttonText="적용하기"
            />
           
        </div>
      </div>
    );
};

Filter.propTypes = {
    visible: PropTypes.bool.isRequired,
    closeModal: PropTypes.func.isRequired,
    contents: PropTypes.node,
};
  
export default Filter;