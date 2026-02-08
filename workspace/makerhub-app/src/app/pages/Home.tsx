import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="min-h-screen flex flex-col bg-gray-50">
      <header className="bg-brand-900 text-white shadow-lg sticky top-0 z-50">
        <div className="container mx-auto px-6 py-4 flex items-center justify-between">
          <div className="flex items-center gap-4">
            <div className="bg-white/10 p-2 rounded-lg backdrop-blur-sm">
              {/* Placeholder for logo if image fails */}
              <div className="h-10 w-10 bg-accent rounded-full flex items-center justify-center font-bold text-brand-900">
                <img src={'assets/logo.png'} alt="logo" />
              </div>
            </div>
            <h1 className="text-2xl font-bold tracking-tight">Universo Maker</h1>
          </div>
          <nav>
            <Link to="/login" className="text-brand-100 hover:text-white transition-colors font-medium">Login</Link>
          </nav>
        </div>
      </header>

      <main className="flex-1">
        {/* Hero Section */}
        <section className="bg-brand-800 text-white py-20 relative overflow-hidden">
          <div className="absolute inset-0 bg-gradient-to-br from-brand-900 to-brand-700 opacity-90"></div>
          <div className="container mx-auto px-6 relative z-10 grid md:grid-cols-2 gap-12 items-center">
            <div className="space-y-6">
              <h2 className="text-4xl md:text-5xl font-bold leading-tight">
                Transformando vidas através do <span className="text-accent">Maker</span> e do <span className="text-accent2">Esporte</span>
              </h2>
              <p className="text-lg text-brand-100 max-w-xl">
                Integração do tratamento e acompanhamento de crianças com transtornos diversos
                por meio de atividades Makers e Esportes adaptados.
              </p>
              <div className="flex flex-wrap gap-4 pt-4">
                <a href="#about" className="border-2 border-white/30 hover:bg-white/10 text-white px-8 py-3 rounded-full font-semibold transition-colors">
                  Saiba mais
                </a>
              </div>
            </div>
            <div className="hidden md:flex justify-center">
              {/* Abstract representation or mascot placeholder */}
              <div className="w-80 h-80 bg-gradient-to-tr from-accent to-accent2 rounded-full opacity-20 blur-3xl absolute"></div>
              <div className="relative bg-white/5 backdrop-blur-md border border-white/10 p-3 rounded-2xl shadow-2xl max-w-sm">
                <div className="space-y-4">
                  <img src="assets/logo.png" alt="logo" />
                </div>
              </div>
            </div>
          </div>
        </section>

        {/* Features Section */}
        <section id="about" className="py-20 container mx-auto px-6">
          <div className="text-center mb-2">
            <h3 className="text-3xl font-bold text-brand-900 mb-4">Como funciona</h3>
            <p className="text-gray-600 max-w-2xl mx-auto">
              Nossa metodologia une tecnologia e atividade física para promover o desenvolvimento integral.
            </p>
          </div>
          <div className="w-80 mx-auto mb-2">
            <img src="assets/mascote.png" alt="mascote" />
          </div>

          <div className="grid md:grid-cols-3 gap-8">
            {[
              { title: "Atividades Maker", desc: "Projetos práticos que estimulam a criatividade e resolução de problemas.", color: "bg-brand-100 text-brand-700" },
              { title: "Esportes Adaptados", desc: "Atividades físicas inclusivas para desenvolvimento motor e social.", color: "bg-accent/10 text-accent2" },
              { title: "Acompanhamento", desc: "Registro detalhado da evolução e relatórios para famílias.", color: "bg-brand-50 text-brand-600" }
            ].map((feature, idx) => (
              <div key={idx} className="bg-white p-8 rounded-xl shadow-sm hover:shadow-md transition-shadow border border-gray-100">
                <div className={`w-12 h-12 ${feature.color} rounded-lg flex items-center justify-center mb-6 font-bold text-xl`}>
                  {idx + 1}
                </div>
                <h4 className="text-xl font-semibold text-brand-900 mb-3">{feature.title}</h4>
                <p className="text-gray-600">{feature.desc}</p>
              </div>
            ))}
          </div>
        </section>
      </main>

      <footer className="bg-brand-900 text-brand-200 py-12 border-t border-brand-800">
        <div className="container mx-auto px-6 flex flex-col md:flex-row justify-between items-center gap-6">
          <div className="text-sm">
            © {new Date().getFullYear()} Universo Maker. Todos os direitos reservados.
          </div>
          <div className="flex gap-6 text-sm font-medium">
            <Link to="/login" className="hover:text-white transition-colors">Área do Colaborador</Link>
            <a href="#" className="hover:text-white transition-colors">Privacidade</a>
            <a href="#" className="hover:text-white transition-colors">Contato</a>
          </div>
        </div>
      </footer>
    </div>
  )
}
