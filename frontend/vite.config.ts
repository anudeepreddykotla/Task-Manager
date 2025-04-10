import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    // 🐳 For use with Docker, comment out when running locally
    host: '0.0.0.0',
    proxy: {
      '/api': {
        // 🐳 For use with Docker, comment out when running locally
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
