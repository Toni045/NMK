import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from '../../pages/Login';
import Logout from '../../pages/Logout';
import Anonymus from '../../pages/Anonymus';
import Authenticated from '../../pages/Authenticated';
import { useAuth0 } from '@auth0/auth0-react';
import { CircularProgress } from '@mui/material';
import { useCallback } from 'react';
import HomePage from '../../pages/Home';
import LaboratoryReports from '../../pages/LaboratoryReports';
import Colors from "../../colors.json";
import LaboratoryValues from '../../pages/LaboratoryValues';

function App() {
  const { isLoading } = useAuth0();
  const getBody = useCallback((): JSX.Element => {
    if (isLoading) {
      return <CircularProgress />
    }
    return <BrowserRouter>
      <Routes>
        <Route path="/laboratoryValues/:laboratoryReportId" element={<LaboratoryValues />} />
        <Route path="/laboratoryReports" element={<LaboratoryReports />} />
        <Route path="/authenticated" element={<Authenticated />} />
        <Route path="/anonymus" element={<Anonymus />} />
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
