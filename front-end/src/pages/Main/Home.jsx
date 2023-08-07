import BottomSheet from "../../components/bottomsheet/BottomSheet";
import { useEffect, useState } from 'react';
import logo from '../../assets/images/logo.png';
import '../Login/login.css';


const Home = () => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [bottomSheetTitle, setBottomSheetTitle] = useState('상세필터');
    const [name, setName] = useState('');

    return (
        <div className="wrap">
        <div className="content">
            <h1>Home에서 테스트 좀 진행하겠습니다</h1>
            <div className="img-box">
                <h3 className="login-title">나의 취향 저장소</h3>
                <img src={logo} className="logo" alt="logo" />
            </div>
            <div className="btn-group">
                <button type="button" className="btn" onClick={() => setIsModalOpen(true)}><span>Open BottomSheet!</span></button>
            </div>
            <BottomSheet title={bottomSheetTitle} closeModal={() => setIsModalOpen(false)}>
                {`내용`}
            </BottomSheet>
        </div>
    </div>
    )
}

export default Home;