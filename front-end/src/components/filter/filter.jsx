import React, {useState} from "react";
import { SelectButton } from 'primereact/selectbutton';
import CommRadioGroup from "../radio/radiogroup";
import CommRadio from "../radio/radio";

function FilterContents() {
  const selectOptions = ['카드형', '리스트형'];
  const [selectValue, setSelectValue] = useState(selectOptions[0]);

  return (
    <div class="content">
      <h3>보기</h3>
      <div className="card flex justify-content-center">
        <SelectButton value={selectValue} onChange={(e) => setSelectValue(e.value)} options={selectOptions}
        pt={{
          button: ({ context }) => ({ className: context.selected ? 'bg-cyan-400 border-cyan-400' : undefined })
        }}></SelectButton>
      </div>
      <h3>정렬</h3>
      <CommRadioGroup label="">
        <CommRadio value="RECENT" name="sort" defaultChecked>최신</CommRadio>
        <CommRadio value="STAR" name="sort">별점</CommRadio>
        <CommRadio value="DATE" name="sort">관람일자</CommRadio>
      </CommRadioGroup>
    </div>
  )
}
  
export default FilterContents;