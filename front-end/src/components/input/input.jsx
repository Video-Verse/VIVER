import React from "react";
import './input.css'

const CommonInp = (props) => {
  console.log('input Props' , props.value);
  console.log('input Props' , props.onChange);
  return (
    <div className="input-box">
      <input type="text" className="input" placeholder="" 
      		 value={props.value} onChange={props.onChange}/>

    </div>
  )
}

export default CommonInp;