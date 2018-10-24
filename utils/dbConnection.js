let mysql = require('mysql')

let sql = mysql.createPool({
    host: '127.0.0.1',
    port: 3306,
    user: 'seohae',
    password: 'seohae',
    database: 'daheonBook'
})

module.exports = sql
