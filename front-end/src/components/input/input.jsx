import React from "react";
import './input.css'

const CommonInp = (props) => {
  return (
    <div className="input-box">
      <input type="text" className="input" placeholder="" />
      <p className="err-msg">[Error Message]</p>
    </div>
  )
}

export default CommonInp;