import logo from './assets/images/logo.png';
import './App.css';
import {useEffect, useState} from "react";

function App() {

  const [message, setMessage] = useState([]);

  useEffect(() => {
    fetch("/helloViver")
        .then((response) => {
			console.log(response);
            return response.json();
        })
        .then(function (data) {
            setMessage(data);
        });
}, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>viver git test</p>

        <ul>
          {message.map((text, index) => <li key={`${index}-${text}`}>{text}</li>)}
        </ul>
      </header>
    </div>
  );
}

export default App;
