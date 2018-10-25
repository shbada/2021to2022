let release = {
    send: function(res, result) {
        return res.status(200).json({
            result : result,
            message: 'success'
        })
    },

    serverError: function (res) {
        return res.status(500).json({
            message: 'server Error'
        })
    },

    clientEerror: function (res) {
        return res.status(400).json({
            message: 'bad Request Error'
        })
    }
}

module.exports = release;
