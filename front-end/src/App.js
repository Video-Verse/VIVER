// import logo from './assets/images/logo.png';
// import logoKr from './assets/images/logo_kr.png'

import 'slick-carousel/slick/slick-theme.css';
import 'slick-carousel/slick/slick.css';
import './styles/reset.css';

//import {useEffect, useState} from "react";

// import Header from './components/Header/Header';
import DockBar from './components/DockBar/DockBar';
import Search from './pages/Search/Search';

function App() {

    return (
    <div>
        {/* <Header /> */}
        {/* <Login /> */}
        {/* <Join /> */}
         {/* <MainSlider/> */}
        <Search />
        <DockBar />
    </div>
 
  );
}
export default App;

