import { useNavigate } from 'react-router-dom';
import logo from '../../logo.png';
import Header from '../Header/Header';
import { HeadingText, HomeStyle } from './Home.styles';

function Home() {
    const navigate = useNavigate();
    return (<div style={{ ...HomeStyle }}>
        <Header />
        <button onClick={() => navigate("/anonymus")}>Test anonymus</button>
        <button onClick={() => navigate("/authenticated")}>Test authenticated</button>
        <h1 style={{ ...HeadingText }}>Welcome to NMK</h1>
        <img src={logo} className="App-logo" alt="logo" />
    </div>);
}

export default Home;