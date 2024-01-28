import React, { useState, useEffect } from 'react';
import 'slick-carousel/slick/slick-theme.css';
import 'slick-carousel/slick/slick.css';
import './styles/reset.css';
import Login from './pages/Login/Login';
import Join from './pages/Login/Join';
import SplashScreen from './pages/Login/SplachScreen';

function App() {
    const [isLoading, setIsLoading] = useState(true);
    
    useEffect(() => {
        //초기 로딩 작업을 수행하고, 작업이 완료되면 setIsLoading(false)를 호출
    
        setTimeout(() => {
          setIsLoading(false);
        }, 1000); // 3초 후에 로딩 완료
      }, []);
    
    return (
        <div>
            {isLoading ? (
                <SplashScreen />
            ) : (
                <Join />
            )}
    </div>
 
  );
}
export default App;

