import React, { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import axios from 'axios';

const SocialLogin = (props) => {
	const [redirectPath, setRedirectPath] = useState(null);

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
					if (response.data.isNewUser) {

						setRedirectPath("/")
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
		console.log('redirectPath', redirectPath);
		return <Navigate to={redirectPath} state={{ from: props.location }} />;
	}

	return <div>Login Loading....</div>;
};

export default SocialLogin;