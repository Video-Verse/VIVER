import React from "react";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";

import SocialLogin from "./components/login/SocialLogin";


function AppRouter() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<App />} />
          <Route path="sociallogin" element={<SocialLogin />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default AppRouter;


