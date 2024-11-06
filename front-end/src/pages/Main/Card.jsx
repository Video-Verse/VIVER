import React, { useState } from "react";
import poster from '../../assets/images/img_sample.png';

const Card = ({ data }) => {
    const [bookmarkStates, setBookmarkStates] = useState({}); // 개별 카드의 북마크 상태 저장

    // 보관함 추가하기
    const addBookmark = (id, type) => {
        console.log(id, type); // 보관함에 추가할 ID 및 타입 출력

        // 해당 카드의 상태 업데이트
        setBookmarkStates(prevState => ({
            ...prevState,
            [id]: !prevState[id] // 클릭할 때마다 상태를 반전시킴
        }));
    };

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
                                <button
                                    className={`btn-bookmark ${bookmarkStates[item.id] ? 'btn-bookmark-after' : ''}`}
                                    onClick={() => addBookmark(item.id, item.category)} // 클릭 시 개별 북마크 상태 업데이트
                                >
                                    <span className="blind">보관함에 저장하기</span>
                                </button>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default Card;
