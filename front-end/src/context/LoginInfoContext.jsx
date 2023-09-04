import React, { createContext, useContext, useState } from 'react';

export const LoginInfoContext = createContext();


export function LoginInfoProvider({ children }) {
	const [loginInfo, setLoginInfo] = useState(null);

	return (
		<LoginInfoContext.Provider value={{ loginInfo, setLoginInfo }}>
			{children}
		</LoginInfoContext.Provider>
	);
};


export function useLoginInfo() {
	const context = useContext(LoginInfoContext);
	if (!context) {
		throw new Error("error");
	}
	return context;
};
