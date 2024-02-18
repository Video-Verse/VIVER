import React, { useEffect, useState } from "react";

import { useLocation } from "react-router-dom";
import { BarChart, Bar, ResponsiveContainer, XAxis } from 'recharts';

import $ from 'jquery';

import './Mypage.css'
import Header from "../../components/Header/Header";
import DockBar from "../../components/DockBar/DockBar";
import isProfile from '../../assets/images/img_lv01.png';
import { useNavigate } from "react-router-dom";

const Mypage = () => {
	
	const location = useLocation();
    const [nickname, setNickname] = useState("");

    useEffect(() => {
        // 로컬 스토리지에서 name 값을 가져와서 설정합니다.
        const storedName = localStorage.getItem("nickname");
        if (storedName) {
            setNickname(storedName);
        }
    }, []);


    useEffect(() => {
        $("#title").css('display', 'none');
        $("#btn-back").css('display', 'none');
        $("#btn-search").css('visibility', 'hidden');
    });
    
    const navigate = useNavigate();

    const data = [
        {name: 'N 월', count: 10},
        {name: 'N 월', count: 5},
        {name: 'N 월', count: 20},
        {name: 'N 월', count: 30},
        {name: 'N 월', count: 8},
    ];

    return (
        <div>
            <Header />
            <div className="wrap">
                <div className="content mypage">
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
                                <span className="fs-20 fw-bold">{nickname}</span>
                                <button className="btn-edit" onClick={() => navigate('/change')} />
                            </div>
                        </div>
                    </div>

                    <div className="chart-box">
                        <ResponsiveContainer width="100%" height="100%">
                            <BarChart width={150} height={40} data={data}>
                                <XAxis
                                    dataKey="name"
                                    axisLine={false} //x축 라인
                                    tickLine={false} //눈금선 
                                    tick={{ fill:'#fff', fontSize: 12, fontWeight: '400' }}
                                />
                                <Bar dataKey="count" fill="#FFCAC3" label={{ position: 'top', fill: '#E50101', fontSize: 14, fontWeight:700 }} />
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
                            <li onClick={() => navigate('/withdrawal')}>회원탈퇴</li>
                        </ul>
                    </div>
                </div>
               
            </div>
            <DockBar/>
        </div>
    )
}

export default Mypage;