import React from "react";
import icClose from '../../assets/images/ic_close.png';
import './bottomsheet.css';

function BottomSheet ({ title, closeModal, children }) {
    return (
      <>
        <div class="modal-background"/>
        <div class="bs">
          <div class="bs-header">
            <div>{title}</div>
            <img src={icClose} alt="x" onClick={closeModal} />
          </div>
          <div>{children}</div>
        </div>
      </>
    );
  }
  export default BottomSheet;