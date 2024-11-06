
import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";

import { LoginInfoProvider } from "./context/LoginInfoContext";

import SocialLogin from "./pages/Login/SocialLogin";
import Join from "./pages/Login/Join";
import Login from "./pages/Login/Login";
import Complete from "./pages/Login/Complete";

import Search from "./pages/Search/Search";
import Nodata from "./pages/Search/Nodata";
import Result from "./pages/Search/Result";

import Home from './pages/Common/Home';
import Recommend from './pages/Common/Recommend';

import Bookmark from "./pages/Mypage/Bookmark";
import NicknameChange from "./pages/Mypage/NicknameChange";
import Mypage from "./pages/Mypage/Mypage";
import Index from "./pages/Login/Index";
import Withdrawal from "./pages/Mypage/Withdrawal";


function AppRouter() {
  return (
    <LoginInfoProvider>
      <BrowserRouter>
        <Routes>
            <Route path="/" element={<App />} />
            <Route path="/oauth/kakao/callback" element={<SocialLogin />} />
            <Route path="/oauth/naver/callback" element={<SocialLogin />} />
            <Route path="/join" element={<Join />} />
            <Route path="/index" element={<Index />} />
            <Route path="/login" element={<Login />} />
            <Route path="/complete" element={<Complete />} />

            <Route path="/home" exact element={<Home />} />
            <Route path="/recommend" exact element={<Recommend />} />
            <Route path="/bookmark" exact element={<Bookmark />} />
                  
            <Route path="/search" exact element= {<Search />} /> 
            <Route path="/result" exact element= {<Result />} />
            <Route path="/nodata" exact element= {<Nodata />} />

            <Route path="/mypage" exact element= {<Mypage />} />
            <Route path="/change" exact element= {<NicknameChange />} />
            <Route path="/withdrawal" exact element= {<Withdrawal />} />


        </Routes>
      </BrowserRouter>
    </LoginInfoProvider>
  );
};

export default AppRouter;


