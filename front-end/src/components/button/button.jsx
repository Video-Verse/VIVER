import React from "react";
import './button.css'


const CommonBtn = (props) => {
  const {onClick, disabled, buttonText  } = props;

  return (
    <div className="btn-group">
        <button type="button" className="btn" disabled={disabled} onClick={onClick}>
            <span>{buttonText}</span>
        </button>
    </div>
  );
}

export default CommonBtn;