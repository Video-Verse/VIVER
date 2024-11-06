import React, {useState} from "react";
import icClose from '../../assets/images/ic_close_white.png';
import CommonBtn from "../../components/button/button";

function BottomSheet ({ title, closeModal, contents, isOpen, buttonText, onBtnClick  }) {
    const [isVisible, setIsVisible] = useState(isOpen);

    // 팝업이 열릴 때 isVisible 상태를 업데이트하여 open 클래스 추가
    useState(() => {
        setIsVisible(isOpen);
    }, [isOpen]);

    // 닫기 버튼 클릭 시 팝업 닫기
    const handleCloseModal = () => {
        setIsVisible(false);
        closeModal();
    };


    return (
        //   <div className="bottom-sheet">
        <div className={`bottom-sheet ${isVisible ? "open" : ""}`}>
            <div className="modal-background"/>
            <div className="bs">
                <div className="bs-header">
                    <img src={icClose} alt="닫기" onClick={handleCloseModal} />
                </div>
                <div className="bs-content">
                    <h1 className="title">{title}</h1>
                    {contents}
                </div>
                <CommonBtn buttonText={buttonText} onClick={onBtnClick} />
            </div>
        </div>
    );
  }
  export default BottomSheet;