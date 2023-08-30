// import logo from './assets/images/logo.png';
// import logoKr from './assets/images/logo_kr.png'

import './styles/reset.css';
//import {useEffect, useState} from "react";
import {createBrowserRouter, RouterProvider} from 'react-router-dom';

import Login from './pages/Login/Login';
import Header from './components/Header/Header';
import Join from './pages/Login/Join';
import Home from './pages/Main/Home';



function App() {

 return(
    <div>

        { <Header /> }
        { <Login /> }
        <Join />
    </div>
    // <div className="wrap">
    //     <div className='content'>
    //             <img src={logo} className="logo" alt="logo" />
    //             <br/>
    //             <img src={logoKr} className="logo-kr" alt="logo-kr" />
                
    //             <div className="btn-group">
    //                 <button type="button" className="btn"><span>확인</span></button>
    //             </div>


    //             <div className="btn-group">
    //                 <button type="button" className="btn" disabled><span>확인</span></button>
    //             </div>

    //             <div className="input-box">
    //                 <input type="text" className="input" placeholder="닉네임" />
    //                 <p className="err-msg">[Error Message]</p>
    //             </div>

    //             <ul>
    //                 {message.map((text, index) => <li key={`${index}-${text}`}>{text}</li>)}
    //             </ul>
    //     </div>
    // </div>
  );
}

export default App;

