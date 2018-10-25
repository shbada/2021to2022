let release = {
    send: function(res, result) {
        return res.status(200).json({
            result : result,
            message: 'success'
        })
    },

    error: function (res) {
        return res.status(500).json({
            message: 'server error'
        })
    }
}

module.exports = release;
