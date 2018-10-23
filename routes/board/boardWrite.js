var express = require('express');
var router = express.Router();
var mysql = require('mysql');

var mysqlInfo = {
    host: '127.0.0.1',
    port: 3306,
    user: 'seohae',
    password: 'seohae',
    database: 'daheonBook'
};

var pool = mysql.createPool(mysqlInfo);

router.post('', function(req, res, next){
    let data = [req.body.title, req.body.content, 1];

    pool.getConnection(function (err, connection) {
        let sql = "INSERT INTO free_board(title, content, writer_idx) VALUES(?,?,?)";

        connection.query(sql, data, function (err, rows) {
            if (err) console.error("err : " + err);

            res.send('작성완료되었어요!')
            connection.release();
        });
    });
});

module.exports = router;
