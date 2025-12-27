import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig({
  plugins: [
    vue(),
    vueDevTools()
  ],

  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },

  server: {
    host: '0.0.0.0',     // BẮT BUỘC để Nginx container truy cập được
    port: 5173,
    strictPort: true,

    // Cho phép truy cập qua domain / proxy
    allowedHosts: 'all',

    // Fix lỗi file không reload trong Docker (Windows / WSL)
    watch: {
      usePolling: true,
      interval: 300
    },

    // Fix HMR khi chạy sau Nginx
    hmr: {
      clientPort: 80
    }
  }
})
