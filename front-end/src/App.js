// import logo from './assets/images/logo.png';
// import logoKr from './assets/images/logo_kr.png'

import './styles/reset.css';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

//import {useEffect, useState} from "react";
import {createBrowserRouter, RouterProvider} from 'react-router-dom';

import Login from './pages/Login/Login';
// import Header from './components/Header/Header';
import Join from './pages/Login/Join';
import Home from './pages/Main/Home';
import Modal from './pages/Common/modal';
import MainSlider from './pages/Common/Home';
import DockBar from './components/DockBar/DockBar';



function App() {

 return(
    <div>
        {/* <Header /> */}
        {/* <Login /> */}
        {/* <Join /> */}
        <MainSlider/>
        <DockBar/>
    </div>
 
  );
}
export default App;

