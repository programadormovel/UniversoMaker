import DashboardLayout from '../../../shared/layouts/DashboardLayout'
import { Link } from 'react-router-dom'

export default function UserHome() {
    return (
        <DashboardLayout>
            <div className="space-y-6">
                <div>
                    <h1 className="text-2xl font-bold text-brand-900">Gerenciamento de Usuários</h1>
                    <p className="text-gray-500">Selecione uma opção abaixo</p>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    <Link to="/users/new" className="block group">
                        <div className="bg-white p-8 rounded-xl shadow-sm border border-gray-100 hover:shadow-md hover:border-brand-200 transition-all text-center h-full flex flex-col items-center justify-center gap-4">
                            <div className="h-16 w-16 bg-brand-50 rounded-full flex items-center justify-center text-brand-600 group-hover:bg-brand-100 group-hover:scale-110 transition-all">
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z" />
                                </svg>
                            </div>
                            <div>
                                <h3 className="text-lg font-semibold text-brand-900 group-hover:text-brand-700">Novo Usuário</h3>
                                <p className="text-sm text-gray-500 mt-1">Cadastrar um novo usuário no sistema</p>
                            </div>
                        </div>
                    </Link>

                    <Link to="/users/list" className="block group">
                        <div className="bg-white p-8 rounded-xl shadow-sm border border-gray-100 hover:shadow-md hover:border-brand-200 transition-all text-center h-full flex flex-col items-center justify-center gap-4">
                            <div className="h-16 w-16 bg-brand-50 rounded-full flex items-center justify-center text-brand-600 group-hover:bg-brand-100 group-hover:scale-110 transition-all">
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                                </svg>
                            </div>
                            <div>
                                <h3 className="text-lg font-semibold text-brand-900 group-hover:text-brand-700">Lista de Usuários</h3>
                                <p className="text-sm text-gray-500 mt-1">Visualizar todos os usuários cadastrados</p>
                            </div>
                        </div>
                    </Link>

                    <Link to="/users/edit" className="block group">
                        <div className="bg-white p-8 rounded-xl shadow-sm border border-gray-100 hover:shadow-md hover:border-brand-200 transition-all text-center h-full flex flex-col items-center justify-center gap-4">
                            <div className="h-16 w-16 bg-brand-50 rounded-full flex items-center justify-center text-brand-600 group-hover:bg-brand-100 group-hover:scale-110 transition-all">
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                                </svg>
                            </div>
                            <div>
                                <h3 className="text-lg font-semibold text-brand-900 group-hover:text-brand-700">Editar Usuário</h3>
                                <p className="text-sm text-gray-500 mt-1">Buscar e editar dados de um usuário</p>
                            </div>
                        </div>
                    </Link>
                </div>
            </div>
        </DashboardLayout>
    )
}
