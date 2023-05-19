import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from '../../pages/Login';
import Logout from '../../pages/Logout';
import { useAuth0 } from '@auth0/auth0-react';
import { CircularProgress } from '@mui/material';
import { useCallback, useContext, useEffect } from 'react';
import HomePage from '../../pages/Home';
import LaboratoryReports from '../../pages/LaboratoryReports';
import Colors from "../../colors.json";
import LaboratoryValues from '../../pages/LaboratoryValues';
import { UserContext } from '../../store/UserContext';
import { ClientsContext } from '../../store/ClientsContext';
import { UserDTO } from '../../api';
import Users from '../../pages/Users';
import LaboratoryValueNames from '../../pages/LaboratoryValueNames';

function App() {
  const { isLoading, isAuthenticated } = useAuth0();
  const { setUser } = useContext(UserContext);
  const { userClient } = useContext(ClientsContext);

  async function managePrincipal() {
    if (isAuthenticated) {
      var user: UserDTO = await userClient.getCurrentUser();
      console.log(user);
      setUser(user);
    } else {
      setUser(undefined);
    }
  }

  useEffect(() => {
    managePrincipal();
  }, [isAuthenticated])

  const getBody = useCallback((): JSX.Element => {
    if (isLoading) {
      return <CircularProgress />
    }
    return <BrowserRouter>
      <Routes>
        <Route path="/laboratoryValueNames" element={<LaboratoryValueNames />} />
        <Route path="/users" element={<Users />} />
        <Route path="/laboratoryValues/:laboratoryReportId" element={<LaboratoryValues />} />
        <Route path="/laboratoryReports" element={<LaboratoryReports />} />
        <Route path="/login" element={<Login />} />
        <Route path="/logout" element={<Logout />} />
        <Route path="/" element={<HomePage />} />
      </Routes>
    </BrowserRouter>
  }, [isLoading]);

  return (<div className='App' style={{ minWidth: '100vw', minHeight: '100vh', backgroundColor: Colors.MintGreen, textAlign: 'center', overflow: "visible" }}>
    {getBody()}
  </div>);
}

export default App;
