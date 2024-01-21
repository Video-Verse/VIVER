let backendHost;

const hostname = window && window.location && window.location.hostname;

if (hostname === "localhost") {
  backendHost = "http://localhost:3000";
} else {
  backendHost = ""; //todo - 운영서버 호스트 
}

export const API_BASE_URL = `${backendHost}`;
