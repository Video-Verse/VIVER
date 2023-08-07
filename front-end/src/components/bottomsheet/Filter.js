import BottomSheet from "./BottomSheet";
import { useEffect, useState } from 'react';

function Filter() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [bottomSheetTitle, setBottomSheetTitle] = useState('상세필터');
    const [name, setName] = useState('');

    return (
        <BottomSheet title={bottomSheetTitle} closeModal={() => setIsModalOpen(false)}>
          {`내용`}
        </BottomSheet>
    );
  }
  
  export default Filter;