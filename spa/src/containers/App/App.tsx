import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from '../../pages/Login';
import Logout from '../../pages/Logout';
import Anonymus from '../../pages/Anonymus';
import Home from '../Home/Home';
import Authenticated from '../../pages/Authenticated';
import { useAuth0 } from '@auth0/auth0-react';
import { CircularProgress } from '@mui/material';
import { useCallback } from 'react';

function App() {
  const { isLoading } = useAuth0();
  const getBody = useCallback((): JSX.Element => {
    if (isLoading) {
      return <CircularProgress />
    }
    return <BrowserRouter>
      <Routes>
        <Route path="/authenticated" element={<Authenticated />} />
        <Route path="/anonymus" element={<Anonymus />} />
        <Route path="/login" element={<Login />} />
        <Route path="/logout" element={<Logout />} />
        <Route path="/" element={<Home />} />
      </Routes>
    </BrowserRouter>
  }, [isLoading]);

  return (<div className='App'>
    {getBody()}
  </div>);
}

export default App;
