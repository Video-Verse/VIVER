import React from "react";

function CommRadio({ children, value, name, defaultChecked, disabled }) {
    return (
        <label>
            <input
                type="radio"
                value={value}
                name={name}
                defaultChecked={defaultChecked}
                disabled={disabled}
            />
            {children}
        </label>
    );
}

export default CommRadio;