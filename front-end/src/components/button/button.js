import React, {Component} from "react";
import './button.css'


const CommonBtn = (props) => {
  const {children, onClick} = props;

  return (
    <div className="btn-group">
        <button type="button" className="btn" disabled><span>확인</span></button>
    </div>
  )
}

export default CommonBtn;