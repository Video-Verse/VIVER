import React, { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import axios from 'axios';
import { useLoginInfo } from "../../context/LoginInfoContext";

const SocialLogin = (props) => {
	const [redirectPath, setRedirectPath] = useState(null);
	const { loginInfo, setLoginInfo } = useLoginInfo();

	const getUrlParameter = (name) => {
		let search = window.location.search;
		let params = new URLSearchParams(search);
		return params.get(name);
	};

	const token = getUrlParameter("token");

	useEffect(() => {
		if (token) {
			localStorage.setItem("ACCESS_TOKEN", token);

			const userType = "KAKAO";

			axios.post("/api/oauth/login", { userType: userType }, {
				headers: {
					'Authorization': `Bearer ${token}`
				}
			})
				.then(response => {
					console.log('resdata',response.data);
					setLoginInfo(response.data);

					if (response.data.newUser) {
						setRedirectPath("/join")
					} else {
						setRedirectPath("/");
					}
				})
				.catch(error => {
					console.error(error);
					setRedirectPath("/login");
				});
		} else {
			setRedirectPath("/login");
		}
	}, [token]);

	if (redirectPath) {
		return <Navigate to={redirectPath} state={{ from: props.location }} />;
	}

	return <div>Login Loading....</div>;
};

export default SocialLogin;