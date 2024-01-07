import React from "react";
import logo from '../../assets/images/logo.png';

const SplashScreen = () => {
    return (
        <div className="wrap">
			<div className="content">
				<div className="img-box">
					<img src={logo} className="logo" alt="logo" />
				</div>
			</div>
		</div>
    )

    
}
export default SplashScreen;