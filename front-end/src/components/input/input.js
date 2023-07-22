import React from "react";
import './input.css'

const CommonInp = (props) => {
  return (
    <div className="input-box">
      <input type="text" className="input" placeholder="닉네임" />
      <p className="err-msg">[Error Message]</p>
    </div>
  )
}

export default CommonInp;