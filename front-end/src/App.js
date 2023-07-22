import logo from './assets/images/logo.png';
import logoKr from './assets/images/logo_kr.png'

import './styles/reset.css';
import {useEffect, useState} from "react";

import CommonBtn from './components/button/button';
import CommonInp from './components/input/input';

function App() {

  const [message, setMessage] = useState([]);

  useEffect(() => {
    fetch("/helloViver")
        .then((response) => {
			console.log(response);
            return response.json();
        })
        .then(function (data) {
            setMessage(data);
        });
}, []);

  return (
    <div className="wrap">
        <div className='content'>
                <img src={logo} className="logo" alt="logo" />
                <br/>
                <img src={logoKr} className="logo-kr" alt="logo-kr" />
                
                <div className="btn-group">
                    <button type="button" className="btn"><span>확인</span></button>
                </div>


                <div className="btn-group">
                    <button type="button" className="btn" disabled><span>확인</span></button>
                </div>

                <div className="input-box">
                    <input type="text" className="input" placeholder="닉네임" />
                    <p className="err-msg">[Error Message]</p>
                </div>

                <ul>
                    {message.map((text, index) => <li key={`${index}-${text}`}>{text}</li>)}
                </ul>
        </div>
    </div>
  );
}

export default App;
