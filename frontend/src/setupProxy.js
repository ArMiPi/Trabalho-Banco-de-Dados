const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/generation',
        createProxyMiddleware({
            target: 'http://localhost:8080', // Atualize com o endereço do seu servidor da API
            changeOrigin: true,
        })
    );
};
