import React, { useEffect } from "react";
import {BarChart, Bar, ResponsiveContainer} from 'recharts';
import $ from 'jquery';

import './Mypage.css'
import Header from "../../components/Header/Header";
import isProfile from '../../assets/images/img_lv01.png';

const Mypage = () => {

    useEffect(() => {
        $("#title").css('display', 'none');
        $("#btn-back").css('display', 'none');
        // $("#logo").css('display', 'none');
		$("#btn-search").css('visibility', 'hidden');
    });

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
            <Header/>
            <div className="wrap">
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
                            <button className="btn-edit"/>
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
                
            </div>

            
        </div>
    )
}

export default Mypage;