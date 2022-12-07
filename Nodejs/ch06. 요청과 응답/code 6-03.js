// 모듈을 추출
var http = require('http');

// server 객체 생성
var server = http.createServer();

// server 객체에 이벤트 연결

// 클라이언트가 요청할때 발생하는 이벤트
server.on('request', function(code){
    console.log('Request On');
});

// 클라이언트가 접속할때 발생하는 이벤트
server.on('connection', function (code) {
    console.log('Connection On');
});

// 서버가 종료될때 발생하는 이벤트
server.on('close',function (code){
    console.log('Close On');
});

// listen() 메서드를 실행
server.listen(52273);