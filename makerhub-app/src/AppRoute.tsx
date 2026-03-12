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
  return isAuth ? children : <Navigate to="/login" replace />;
};

const PublicRoute: React.FC<PrivateRouteProps> = ({ children }) => {
  const isAuth = authService.isAuthenticated();
  return isAuth ? <Navigate to="/app" replace /> : children;
};

const AppRoute = () => {
    console.log('AppRoute renderizado');
    const defaultRoute = authService.isAuthenticated() ? '/app' : '/login';
    return (
        <Routes>
            <Route path="/" element={<Navigate to={defaultRoute} replace />} />
            <Route path="/login" element={<PublicRoute><Login /></PublicRoute>} />
            <Route path="/app" element={<PrivateRoute><App /></PrivateRoute>} />
            <Route path="/dashboard" element={<PrivateRoute><DashboardPage /></PrivateRoute>} />
            <Route path="/pei" element={<PrivateRoute><PEIPage /></PrivateRoute>} />
            <Route path="*" element={<Navigate to={defaultRoute} replace />} />
        </Routes>
    )
}

export default AppRoute
