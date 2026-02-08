import { Route, Routes } from "react-router-dom"
import App from "./App"
import DashboardPage from "./pages/DashboardPage"
import PEIPage from "./pages/PEIPage"

const AppRoute = () => {
    return (
        <Routes>
            <Route path="/" element={<App />} />
            <Route path="/dashboard" element={<DashboardPage />} />
            <Route path="/pei" element={<PEIPage />} />
        </Routes>
    )
}

export default AppRoute