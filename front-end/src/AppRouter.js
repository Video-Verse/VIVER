import React, { useState } from "react";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";

import SocialLogin from "./components/login/SocialLogin";
import Join from "./pages/Login/Join";
import { LoginInfoProvider } from "./context/LoginInfoContext";

import Search from "./pages/Search/Search";
import Nodata from "./pages/Search/Nodata";

function AppRouter() {
  return (
    <LoginInfoProvider>
      <BrowserRouter>
        <Routes>
            <Route path="/" element={<App />} />
            <Route path="sociallogin" element={<SocialLogin />} />
            <Route path="join" element={<Join />} />

            <Route path="/search" exact element= {<Search />} /> 
            <Route path="/nodata" exact element= {<Nodata />} />
               
        </Routes>
      </BrowserRouter>
    </LoginInfoProvider>
  );
};

export default AppRouter;


