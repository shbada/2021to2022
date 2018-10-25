let returnUtils = {
    send: function(res) {
        if(res.status(200)) {
            res.send(200, '성공')
        } else if (res.status(500)) {
            res.send('서버에러가 발생하였습니다.')
        } else if (res.status(304)) {
            res.send('요청이 잘못되었습니다.')
        }
    },

    send: function(res, result) {
        if(res.status(200)) {
            res.send(result)
        } else if (res.status(500)) {
            res.send('서버에러가 발생하였습니다.')
        } else if (res.status(304)) {
            res.send('요청이 잘못되었습니다.')
        }
    },

}

module.exports = returnUtils;
