import './App.css';
import { BrowserRouter, HashRouter, Route, Routes } from 'react-router-dom';
import Login from '../../pages/Login';
import Logout from '../../pages/Logout';
import Anonymus from '../../pages/Anonymus';
import Home from '../Home/Home';
import Authenticated from '../../pages/Authenticated';

function App() {

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/authenticated" element={<Authenticated />} />
          <Route path="/anonymus" element={<Anonymus />} />
          <Route path="/login" element={<Login />} />
          <Route path="/logout" element={<Logout />} />
          <Route path="/" element={<Home />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
