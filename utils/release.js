let release = {
    send: function(res, result) {
        return res.status(200).json({
            result : result,
            message: 'success'
        })
    }
}

module.exports = release;
