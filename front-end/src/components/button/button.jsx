import React, {Component} from "react";
import './button.css'

// function kepadOpenInpTxt(){
//     $('input[type=text]').focusin(function() {
//        $('.btn-group').css("position","initial");
//     });
//     $('input[type=text]').focusout(function() {
//         $('.btn-group').css("position","fixed");
//     });
// };

const CommonBtn = (props) => {
  const {children, onClick} = props;

  return (
    <div className="btn-group">
        <button type="button" className="btn" disabled><span>확인</span></button>
    </div>
  )
}

export default CommonBtn;