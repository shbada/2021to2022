let mysql = require('mysql')

let sql = mysql.createPool({
    host: '127.0.0.1',
    port: 3306,
    user: 'seohae',
    password: 'seohae',
    database: 'daheonBook'
})

sql.promiseQuery = function (query, params) {
    let that = this
    return new Promise(function (resolved, rejected) {
        try {
            that.query(query, params, function (err, rows) {
                if (err) {
                    rejected(err)
                }
                else {
                    resolved(rows)
                }
            })
        }
        catch (e) {
            console.log(e)
            rejected(e)
        }
    })
}

module.exports = sql
