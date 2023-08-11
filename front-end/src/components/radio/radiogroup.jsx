import React from "react";

function CommRadioGroup({ label, children }) {
  return (
    <fieldset>
      <legend>{label}</legend>
      {children}
    </fieldset>
  );
}

export default CommRadioGroup;