import { Routes, Route } from 'react-router-dom'
import Home from '../pages/Home'
import UsersPage, { UserCreate, UserList, UserEdit } from '../../domains/user/pages'
import Login from '../pages/Login'
import ForgotPassword from '../pages/ForgotPassword'
import ContactAdmin from '../pages/ContactAdmin'
import Dashboard from '../pages/Dashboard'
import MessageList from '../../domains/shared_kernel/message_to_admin/pages'
import ProtectedRoute from './ProtectedRoute'
import Settings from '../pages/Settings'

export default function AppRoutes() {
   return (
      <Routes>
         <Route path="/" element={<Home />} />
         <Route path="/users" element={<UsersPage />} />
         <Route path="/users/new" element={<UserCreate />} />
         <Route path="/users/list" element={<UserList />} />
         <Route path="/users/edit" element={<UserEdit />} />
         <Route path="/login" element={<Login />} />
         <Route path="/forgot-password" element={<ForgotPassword />} />
         <Route path="/contact-admin" element={<ContactAdmin />} />
         <Route path="/messages" element={<MessageList />} />
         <Route path="/settings" element={<Settings />} />
         <Route path="/dashboard" element={<ProtectedRoute><Dashboard /></ProtectedRoute>} />
      </Routes>
   )
}
