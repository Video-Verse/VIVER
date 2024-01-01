import React from "react";
import icClose from '../../assets/images/ic_close.png';
import './bottomsheet.css';
import { prototype } from "react-modal";

function Filter ({ title, visible, closeModal, contents }) {
    return (
      <div className={`bottom-sheet ${visible ? 'open' : ''}`}>
        <div class="modal-background"/>
        <div class="bs">
            <div class="bs-header">
                <img src={icClose} alt="닫기" onClick={closeModal} />
            </div>
            <div class="bs-content">
                <h1>{title}</h1>
                <div>{contents}</div>
            </div>
        </div>
      </div>
    );
};

export default Filter;