import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter } from 'react-router-dom'
import './index.css'
import AppRoute from './AppRoute.tsx'
import 'bootstrap/dist/css/bootstrap.min.css'

// Wait for DOM to be ready
const initApp = function() {
  const rootElement = document.getElementById('root')
  if (!rootElement) {
    console.error('Root element not found')
    return
  }

  const root = createRoot(rootElement)
  root.render(
    <StrictMode>
      <BrowserRouter>
        <AppRoute />
      </BrowserRouter>
    </StrictMode>
  )

  // Hide splash screen after React renders
  setTimeout(function() {
    import('@capacitor/splash-screen').then(function(module) {
      if (module.SplashScreen) {
        module.SplashScreen.hide()
      }
    }).catch(function(e) {
      console.log('SplashScreen not available:', e)
    })
  }, 500)
}

if (document.readyState === 'loading') {
  document.addEventListener('DOMContentLoaded', initApp)
} else {
  initApp()
}