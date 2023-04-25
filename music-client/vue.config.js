const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  chainWebpack: config => {
      config.plugin('html').tap(args => {
          args[0].title = '音乐网'
          return args
      })

      config.plugin('define').tap(definitions => {
          Object.assign(definitions[0]['process.env'], {
              NODE_HOST: '"http://localhost:8888"',
          });
          return definitions;
      });
  }
})
