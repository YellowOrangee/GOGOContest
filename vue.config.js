// const { defineConfig } = require('@vue/cli-service')
module.exports = {
    // transpileDependencies: true,
    devServer: {
        proxy: {
            '/gogoContest': {
                target: 'http://60.205.2.92:8080/gogoContest',
                changeOrigin: true,
                pathRewrite: {
                    '^/gogoContest': ''
                }
            }
        }
    }
}

