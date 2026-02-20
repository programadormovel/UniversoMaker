import { Route, Routes, Navigate } from "react-router-dom"
import { JSX } from 'react'
import App from "./App"
import DashboardPage from "./pages/DashboardPage"
import PEIPage from "./pages/PEIPage"
import Login from "./pages/Login"
import { authService } from "./services/authService"

interface PrivateRouteProps {
  children: JSX.Element;
}

const PrivateRoute: React.FC<PrivateRouteProps> = ({ children }) => {
  const isAuth = authService.isAuthenticated();
  console.log('PrivateRoute - isAuthenticated:', isAuth);
  console.log('PrivateRoute - token:', authService.getToken());
  return isAuth ? children : <Navigate to="/" replace />;
};

const AppRoute = () => {
    console.log('AppRoute renderizado');
    return (
        <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/app" element={<PrivateRoute><App /></PrivateRoute>} />
            <Route path="/dashboard" element={<PrivateRoute><DashboardPage /></PrivateRoute>} />
            <Route path="/pei" element={<PrivateRoute><PEIPage /></PrivateRoute>} />
        </Routes>
    )
}

export default AppRoute