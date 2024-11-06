import React from "react";

const CommonBtnType2 = (props) => {
  const {onClick, disabled, buttonText1, buttonText2, onBtnClick1, onBtnClick2  } = props;

  return (
    <div className="btn-group type2">
        <button type="button" className="btn-second" disabled={disabled} onClick={onBtnClick1}>
            <span>{buttonText1}</span>
        </button>
        <button type="button" className="btn" disabled={disabled} onClick={onBtnClick2}>
            <span>{buttonText2}</span>
        </button>
    </div>
  );
}

export default CommonBtnType2;