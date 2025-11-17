import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import { VitePWA } from 'vite-plugin-pwa'

export default defineConfig({
  build: {
    target: ['es2015', 'chrome58', 'firefox57', 'safari11'],
    minify: 'terser',
    terserOptions: {
      ecma: 2015,
      compress: {
        drop_console: false,
        drop_debugger: false
      }
    }
  },
  plugins: [
    react(),
    VitePWA({
      registerType: 'autoUpdate',
      includeAssets: ['favicon.ico', 'logo-app-v4.jpg', 'mascote.png'],
      manifest: {
        name: 'Universo Maker',
        short_name: 'Universo Maker',
        description: 'Aplicativo para avaliação diária de alunos com necessidades especiais',
        theme_color: '#ffffff',
        background_color: '#ffffff',
        display: 'standalone',
        orientation: 'portrait',
        scope: '/',
        start_url: '/',
        icons: [
          {
            src: 'logo-app-v4.jpg',
            sizes: '192x192',
            type: 'image/jpeg'
          },
          {
            src: 'logo-app-v4.jpg',
            sizes: '512x512',
            type: 'image/jpeg'
          }
        ]
      },
      workbox: {
        globPatterns: ['**/*.{js,css,html,ico,png,svg}']
      }
    })
  ],
})