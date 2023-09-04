import React from "react";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";

import SocialLogin from "./components/login/SocialLogin";
import Join from "./pages/Login/Join";
import { LoginInfoProvider } from "./context/LoginInfoContext";


function AppRouter() {
  return (
    <LoginInfoProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<App />} />
          <Route path="sociallogin" element={<SocialLogin />} />
          <Route path="join" element={<Join />} />
        </Routes>
      </BrowserRouter>
    </LoginInfoProvider>
  );
};

export default AppRouter;


