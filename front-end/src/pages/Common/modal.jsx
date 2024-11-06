import React, {useState} from 'react';

const Modal = ({title, isOpen, content, modalText1, modalText2, type, btnClick01, btnClick02}) => {

    const [isVisible, setIsVisible] = useState(isOpen);

    // 팝업이 열릴 때 isVisible 상태를 업데이트하여 open 클래스 추가
    useState(() => {
        setIsVisible(isOpen);
    }, [isOpen]);

    return (
        <div className={`modal-wrapper ${isVisible ? 'open': ''}`}>
            <div className='modal-bg'></div>
            <div className='modal-pop'>
                <div className={`modal ${type === 'notice' ? 'type-notice' : ''}`}>
                    <div className="modal-head">
                    {type === 'notice' && <i className="icon ic-notice" />}
                        <h3 className="title">{title}</h3>
                    </div>
                    <div className="modal-content">
                        {content}
                    </div>
                    <div className="modal-foot">
                        <button className="btn1" onClick={btnClick01}>{modalText1}</button>
                        <button className="btn2" onClick={btnClick02}>{modalText2}</button>
                    </div>
                </div>
            </div>
        </div>
           
   
  );
}

export default Modal;
