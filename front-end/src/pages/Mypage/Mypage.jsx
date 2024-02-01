import React, { useEffect, useState } from "react";
import { BarChart, Bar, ResponsiveContainer } from 'recharts';
import $ from 'jquery';

import './Mypage.css'
import Header from "../../components/Header/Header";
import DockBar from "../../components/DockBar/DockBar";
import isProfile from '../../assets/images/img_lv01.png';
import { useNavigate } from "react-router-dom";

const Mypage = () => {

    useEffect(() => {
        $("#title").css('display', 'none');
        $("#btn-back").css('display', 'none');
        // $("#logo").css('display', 'none');
        $("#btn-search").css('visibility', 'hidden');
    });
    
    const navigate = useNavigate();
    const [hasDockBar, setHasDockBar] = useState(true);

    const data = [
        {
            name: 'N 월',
            uv: 10,
            // pv: 2400,
            // amt: 2400,
        },
        {
            name: 'N 월',
            uv: 8,
            // pv: 2400,
            // amt: 2400,
        },
        {
            name: 'N 월',
            uv: 4000,
            // pv: 2400,
            // amt: 2400,
        },
        {
            name: 'N 월',
            uv: 15,
            // pv: 2400,
            // amt: 2400,
        },
        {
            name: 'N 월',
            uv: 5,
            // pv: 2400,
            // amt: 2400,
        }
    ]


    return (
        <div>
            <Header />
            <div className={`wrap ${hasDockBar ? 'has-dockbar' : ''}`}>
                <div className="profile-img">
                    <img src={isProfile} alt="lv01" />
                </div>

                <div className="my-info">
                    <div className="level">
                        <span>내 레벨</span>
                        <span className="fs-20 fc-red fw-bold">1 Lv</span>
                    </div>

                    <div className="name">
                        <span>닉네임</span>
                        <div>
                            <span className="fs-20 fw-bold">viver</span>
                            <button className="btn-edit" onClick={() => navigate('/change')} />
                        </div>
                    </div>
                </div>

                <div className="chart-box">
                    <ResponsiveContainer width="100%" height="100%">
                        <BarChart width={150} height={40} data={data}>
                            <Bar dataKey="uv" fill="#8884d8" />
                        </BarChart>
                    </ResponsiveContainer>
                </div>

                <div className="link-list">
                    <ul>
                        <li onClick={() => navigate('/')}>화면설정</li>
                        <li onClick={() => navigate('/')}>고객센터</li>
                        <li onClick={() => navigate('/')}>개인정보처리방침</li>
                        <li onClick={() => navigate('/')}>이용약관</li>
                    </ul>
                </div>

                <div className="util-list">
                    <ul>
                        <li>로그아웃</li>
                        <li>회원탈퇴</li>
                    </ul>
                </div>
            </div>
            <DockBar/>
        </div>
    )
}

export default Mypage;