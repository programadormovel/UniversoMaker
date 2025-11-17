import { Route, Routes } from "react-router-dom"
import App from "./App"
import DashboardPage from "./pages/DashboardPage"

const AppRoute = () => {
    return (
        <Routes>
            <Route path="/" element={<App />} />
            <Route path="/dashboard" element={<DashboardPage />} />
        </Routes>
    )
}

export default AppRoute