const express = require('express');
const server = express();
const livros = require('./src/data/livros.json')


server.get('/', (req,res) => {
    return res.json({mensagem: 'Bem vindo à Biblioteca online!'})
});

server.get('/livros', (req,res) => {
    return res.json(livros)
});

server.listen(3000, () => {
    console.log('O servidor está funcionando...')
});