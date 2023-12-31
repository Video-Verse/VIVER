import React, { useEffect, useState } from "react";
import styled, { keyframes } from 'styled-components';
import './ToastPop.css';

const fadeIn = keyframes`
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
`;

const fadeOut = keyframes`
    from {
        opacity: 1;
    }
    to {
        opacity: 0;
    }
`;

const ToastPop = ({ message, isVisible, onClose, closeButtonText }) => {
    const [visible, setVisible] = useState(isVisible);
    
    useEffect(() => {
        setVisible(isVisible);
        if (isVisible) {
            const timer = setTimeout(() => {
                setVisible(false);
                onClose();
            }, 3000);
            return () => clearTimeout(timer);
        }
    }, [isVisible, onclose]);
    
    
    return visible ? (
        <div className="toast-wrap">
            <span>{message}</span>
            <button className="btn-del" onClick={() => onclose()}>
                {closeButtonText || '닫기'}
            </button>
        </div>
    ) : null;
};

export default ToastPop;