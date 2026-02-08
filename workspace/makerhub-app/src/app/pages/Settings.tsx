import { useNavigate } from 'react-router-dom'
import DashboardLayout from '../../shared/layouts/DashboardLayout'

interface ConfigurationItem {
    id: string
    name: string
    description: string
    path: string
    icon: React.ReactNode
}

interface ConfigurationDomain {
    domain: string
    items: ConfigurationItem[]
}

export default function Settings() {
    const navigate = useNavigate()

    const configurationDomains: ConfigurationDomain[] = [
        {
            domain: 'Avaliação',
            items: [
                {
                    id: 'classification',
                    name: 'Classificação',
                    description: 'Gerenciar classificações de avaliação',
                    path: '/settings/classification',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01" />
                        </svg>
                    ),
                },
                {
                    id: 'evaluation-type',
                    name: 'Tipo de Avaliação',
                    description: 'Gerenciar tipos de avaliação',
                    path: '/settings/evaluation-type',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
                        </svg>
                    ),
                },
                {
                    id: 'evaluation-item',
                    name: 'Item de Avaliação',
                    description: 'Gerenciar itens de avaliação',
                    path: '/settings/evaluation-item',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
                        </svg>
                    ),
                },
                {
                    id: 'question-category',
                    name: 'Categoria de Questão',
                    description: 'Gerenciar categorias de questões',
                    path: '/settings/question-category',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8.228 9c.549-1.165 2.03-2 3.772-2 2.21 0 4 1.343 4 3 0 1.4-1.278 2.575-3.006 2.907-.542.104-.994.54-.994 1.093m0 3h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                        </svg>
                    ),
                },
            ],
        },
        {
            domain: 'Instituição',
            items: [
                {
                    id: 'institution-type',
                    name: 'Tipo de Instituição',
                    description: 'Gerenciar tipos de instituição',
                    path: '/settings/institution-type',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
                        </svg>
                    ),
                },
                {
                    id: 'professional-role',
                    name: 'Função Profissional',
                    description: 'Gerenciar funções profissionais',
                    path: '/settings/professional-role',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                        </svg>
                    ),
                },
            ],
        },
        {
            domain: 'PEI',
            items: [
                {
                    id: 'educational-need',
                    name: 'Necessidade Educacional',
                    description: 'Gerenciar necessidades educacionais',
                    path: '/settings/educational-need',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.747 0 3.332.477 4.5 1.253v13C19.832 18.477 18.247 18 16.5 18c-1.746 0-3.332.477-4.5 1.253" />
                        </svg>
                    ),
                },
                {
                    id: 'function-type',
                    name: 'Tipo de Função',
                    description: 'Gerenciar tipos de função',
                    path: '/settings/function-type',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M7 21a4 4 0 01-4-4V5a2 2 0 012-2h4a2 2 0 012 2v12a4 4 0 01-4 4zm0 0h12a2 2 0 002-2v-4a2 2 0 00-2-2h-2.343M11 7.343l1.657-1.657a2 2 0 012.828 0l2.829 2.829a2 2 0 010 2.828l-8.486 8.485M7 17h.01" />
                        </svg>
                    ),
                },
            ],
        },
        {
            domain: 'Pessoa',
            items: [
                {
                    id: 'person-type',
                    name: 'Tipo de Pessoa',
                    description: 'Gerenciar tipos de pessoa',
                    path: '/settings/person-type',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                        </svg>
                    ),
                },
                {
                    id: 'relationship-type',
                    name: 'Tipo de Relacionamento',
                    description: 'Gerenciar tipos de relacionamento',
                    path: '/settings/relationship-type',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                        </svg>
                    ),
                },
            ],
        },
        {
            domain: 'Profissional',
            items: [
                {
                    id: 'class-council',
                    name: 'Conselho de Classe',
                    description: 'Gerenciar conselhos de classe',
                    path: '/settings/class-council',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
                        </svg>
                    ),
                },
                {
                    id: 'specialty',
                    name: 'Especialidade',
                    description: 'Gerenciar especialidades',
                    path: '/settings/specialty',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M11.049 2.927c.3-.921 1.603-.921 1.902 0l1.519 4.674a1 1 0 00.95.69h4.915c.969 0 1.371 1.24.588 1.81l-3.976 2.888a1 1 0 00-.363 1.118l1.518 4.674c.3.922-.755 1.688-1.538 1.118l-3.976-2.888a1 1 0 00-1.176 0l-3.976 2.888c-.783.57-1.838-.197-1.538-1.118l1.518-4.674a1 1 0 00-.363-1.118l-3.976-2.888c-.784-.57-.38-1.81.588-1.81h4.914a1 1 0 00.951-.69l1.519-4.674z" />
                        </svg>
                    ),
                },
            ],
        },
        {
            domain: 'Compartilhado',
            items: [
                {
                    id: 'contact-type',
                    name: 'Tipo de Contato',
                    description: 'Gerenciar tipos de contato',
                    path: '/settings/contact-type',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                        </svg>
                    ),
                },
            ],
        },
        {
            domain: 'Usuário',
            items: [
                {
                    id: 'role',
                    name: 'Função de Usuário',
                    description: 'Gerenciar funções de usuário',
                    path: '/settings/role',
                    icon: (
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M15 7a2 2 0 012 2m4 0a6 6 0 01-7.743 5.743L11 17H9v2H7v2H4a1 1 0 01-1-1v-2.586a1 1 0 01.293-.707l5.964-5.964A6 6 0 1121 9z" />
                        </svg>
                    ),
                },
            ],
        },
    ]

    const handleNavigate = (path: string) => {
        navigate(path)
    }

    return (
        <DashboardLayout>
            <div className="space-y-6">
                {/* Header */}
                <div className="mb-8">
                    <h1 className="text-4xl font-bold text-brand-900 mb-2">Configurações do Sistema</h1>
                    <p className="text-brand-600">Gerencie as configurações e parâmetros do sistema</p>
                </div>

                {/* Configuration Domains */}
                <div className="space-y-8">
                    {configurationDomains.map((domain) => (
                        <div key={domain.domain} className="bg-white rounded-xl shadow-lg p-6 border border-brand-100">
                            <h2 className="text-2xl font-semibold text-brand-800 mb-4 flex items-center gap-2">
                                <div className="w-1 h-6 bg-accent rounded-full"></div>
                                {domain.domain}
                            </h2>

                            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4">
                                {domain.items.map((item) => (
                                    <button
                                        key={item.id}
                                        onClick={() => handleNavigate(item.path)}
                                        className="group relative bg-gradient-to-br from-white to-brand-50 border-2 border-brand-200 rounded-lg p-6 hover:border-accent hover:shadow-xl transition-all duration-300 text-left overflow-hidden"
                                    >
                                        {/* Hover effect background */}
                                        <div className="absolute inset-0 bg-gradient-to-br from-accent/5 to-brand-500/5 opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>

                                        {/* Content */}
                                        <div className="relative z-10">
                                            <div className="text-brand-600 group-hover:text-accent transition-colors duration-300 mb-3">
                                                {item.icon}
                                            </div>
                                            <h3 className="text-lg font-semibold text-brand-900 mb-2 group-hover:text-accent transition-colors duration-300">
                                                {item.name}
                                            </h3>
                                            <p className="text-sm text-brand-600 group-hover:text-brand-700 transition-colors duration-300">
                                                {item.description}
                                            </p>
                                        </div>

                                        {/* Arrow indicator */}
                                        <div className="absolute bottom-4 right-4 text-brand-400 group-hover:text-accent group-hover:translate-x-1 transition-all duration-300">
                                            <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5l7 7-7 7" />
                                            </svg>
                                        </div>
                                    </button>
                                ))}
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </DashboardLayout>
    )
}
