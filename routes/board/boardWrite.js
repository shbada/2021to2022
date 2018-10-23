var express = require('express');
var router = express.Router();
var mysql = require('mysql');

var connection = mysql.createConnection({
    host: 'localhost',
    port: 3306,
    user: 'daheon',
    password: 'daheon',
    database: 'daheon'
});

router.post('',function (req, res, next) {
    res.send('board Write!')
})

module.exports = router;
