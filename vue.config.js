// const { defineConfig } = require('@vue/cli-service')
module.exports = {
    // transpileDependencies: true,
    devServer: {
        proxy: {
            '/api': {
                target: 'http://60.205.2.92:8080/gogoContest',
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}

