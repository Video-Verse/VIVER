import React, { useState } from "react";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";

import SocialLogin from "./pages/Login/SocialLogin";
import Join from "./pages/Login/Join";
import { LoginInfoProvider } from "./context/LoginInfoContext";

import Search from "./pages/Search/Search";
import Nodata from "./pages/Search/Nodata";
import Result from "./pages/Search/Result";
import Home from './pages/Common/Home';
import Bookmark from "./pages/Mypage/Bookmark";

function AppRouter() {
  return (
    <LoginInfoProvider>
      <BrowserRouter>
        <Routes>
            <Route path="/" element={<App />} />
            <Route path="/oauth/kakao/callback" element={<SocialLogin />} />
            <Route path="/join" element={<Join />} />

            <Route path="/home" exact element={<Home />} />
            <Route path="/bookmark" exact element={<Bookmark />} />
                  
            <Route path="/search" exact element= {<Search />} /> 
            <Route path="/result" exact element= {<Result />} />
            <Route path="/nodata" exact element= {<Nodata />} />
        </Routes>
      </BrowserRouter>
    </LoginInfoProvider>
  );
};

export default AppRouter;


