const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  pages: {
    index: {
      entry: 'src/pages/page-index/main.js',
      template: 'src/pages/page-index/index.html',
      filename: 'index1.html',
    },
    login: {
      entry: 'src/pages/page-login/main.js',
      template: 'src/pages/page-login/index.html',
      filename: 'login.html',
    },
    personal: {
      entry: 'src/pages/page-personal/main.js',
      template: 'src/pages/page-personal/index.html',
      filename: 'personal.html'
    },
    info: {
      entry: 'src/pages/page-info/main.js',
      template: 'src/pages/page-info/index.html',
      filename: 'info.html'
    }
  },
  devServer: {
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:10086',
        ws: true,
        pathRewrite: {
          '^/api': ''
        },
        changeOrigin: true
      }
    }
  }
})
