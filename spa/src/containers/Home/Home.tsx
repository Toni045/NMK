import { useNavigate } from 'react-router-dom';
import logo from '../../logo.svg';

function Home() {
    const navigate = useNavigate();
    return (<div>
        <button onClick={() => navigate("/anonymus")}>Test anonymus</button>
        <button onClick={() => navigate("/authenticated")}>Test authenticated</button>
        <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <p>
                Edit <code>src/App.tsx</code> and save to reload.
            </p>
            <a
                className="App-link"
                href="https://reactjs.org"
                target="_blank"
                rel="noopener noreferrer"
            >
                Learn React
            </a>
        </header>
    </div>);
}

export default Home;