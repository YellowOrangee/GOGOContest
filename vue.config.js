// const { defineConfig } = require('@vue/cli-service')
module.exports = {
    // transpileDependencies: true,
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8080/SSM__04__GGC_2',
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}

