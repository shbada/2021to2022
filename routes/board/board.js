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
        let sql = "INSERT INTO free_board(title, content, writer_idx) VALUES(?, ?, ?)";

        connection.query(sql, data, function (err, rows) {
            if (err) console.error("err : " + err);

            res.send('작성완료되었어요!')
            connection.release();
        });
    });
});

router.get('', function(req, res, next){
    pool.getConnection(function (err, connection) {
        let sql = "SELECT idx, title, content, view_cnt AS viewCnt, like_cnt AS likeCnt, writer_idx AS writerIdx, created_date AS createdDate FROM free_board ORDER BY idx";

        connection.query(sql, function (err, rows) {
            if (err) console.error("err : " + err);

            res.send({ rows: rows });

            connection.release();
        });
    });
});

router.get('/:boardIdx', function(req, res, next){
    pool.getConnection(function (err, connection) {
        let data = [req.params.boardIdx];

        let sql = "SELECT idx, title, content, view_cnt AS viewCnt, like_cnt AS likeCnt, writer_idx AS writerIdx, created_date AS createdDate FROM free_board WHERE idx = ?";

        connection.query(sql, data, function (err, rows) {
            if (err) console.error("err : " + err);

            res.send({ rows: rows });

            connection.release();
        });
    });
});

router.put('/:idx', function(req, res, next){
    let data = [req.body.title, req.body.content, req.params.idx];

    pool.getConnection(function (err, connection) {
        let sql = "UPDATE free_board SET title = ?, content = ?, updated_date = NOW() WHERE idx = ?";

        connection.query(sql, data, function (err, rows) {
            if (err) console.error("err : " + err);

            connection.release();
        });
    });
});

router.delete('/:idx', function(req, res, next){
    let data = [req.params.idx];

    pool.getConnection(function (err, connection) {
        let sql = "DELETE FROM free_board WHERE idx = ?";

        connection.query(sql, data, function (err, rows) {
            if (err) console.error("err : " + err);

            let sql2 = "SELECT idx, title, content, view_cnt AS viewCnt, like_cnt AS likeCnt, writer_idx AS writerIdx, created_date AS createdDate FROM free_board ORDER BY idx";

            connection.query(sql2, data, function (err, rows) {
                if (err) console.error("err : " + err);
                res.send({ rows: rows });

                connection.release();
            });
        });
    });
});

module.exports = router;
