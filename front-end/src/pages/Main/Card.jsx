import React from "react";

import poster from '../../assets/images/img_sample.png';

const Card = ({ data }) => {
    
    return (
        <div>
            <div className="content">
                <div className="view-area">
                    <ul className="list-wrap">
                        {data.map(item => (
                            <li key={item.id}>
                                <a href="#none">
                                    <div className="img-box">
                                        <img src={poster} alt="poster" className="poster"/>
                                    </div>
                                    <span className="name">{item.title}</span>
                                </a>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    )
};


export default Card;