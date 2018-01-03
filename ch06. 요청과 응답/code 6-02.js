// 서버 생성
var server = require('http').createServer();
server.listen(52273, function(){
    console.log('서버를 실행하였다.');
});

// 10 초 후 함수를 실행
var test = function() {    
    // 서버를 종료
    server.close();
};

setTimeout(test, 1000);
