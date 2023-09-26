import React, { Children, useState } from 'react';
import '../Common/modal.css';

function Modal(props) {
  const [isOpen, setIsOpen] = useState(false);

  const openModal = () => {
    setIsOpen(true);
  };

  const closeModal = () => {
    setIsOpen(false);
  };

    return (
        <div>
            {/* 팝업 오픈 임시버튼 */}
            <button onClick={openModal} style={{color:"#fff"}}>모달 열기</button>
            {/* 팝업 오픈 임시버튼 */}

            {/* 팝업영역 */}
            {isOpen && (
                <div>
                    <div className='modal-bg'></div>
                    <div className='modal-pop'>
                        <div className="modal">
                            <div className="modal-head">
                                <i className="icon ic-notice"></i>
                                <h3 className="title">안내</h3>
                            </div>
                            <div className="modal-content">
                                {props.children}
                            </div>
                            <div className="modal-foot">
                                <button className="btn1">취소</button>
                                <button className="btn2" onClick={closeModal}>닫기</button>
                            </div>
                        </div>
                    </div>
                </div>
                
            )}
           
        </div>
   
  );
}

export default Modal;
