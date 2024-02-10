import React from "react";
import './button.css'


const CommonBtnType2 = (props) => {
  const {onClick, disabled, buttonText1, buttonText2  } = props;

  return (
    <div className="btn-group type2">
        <button type="button" className="btn-second" disabled={disabled} onClick={onClick}>
            <span>{buttonText1}</span>
        </button>
        <button type="button" className="btn" disabled={disabled} onClick={onClick}>
            <span>{buttonText2}</span>
        </button>
    </div>
  );
}

export default CommonBtnType2;