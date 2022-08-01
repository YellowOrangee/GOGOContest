const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // devServer:{
  //   proxy:{
  //     '/api':{
  //       target:'http://localhost:8080/SSM__04__SSC',
  //       pathRewrite:{'^/api':''},
  //     }
  //   }
  // }
})
