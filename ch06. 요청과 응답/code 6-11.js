// 모듈을 추출
var fs = require('fs');
var http = require('http');

// 52273번 포트에 서버를 생성하고 실행
http.createServer(function (request, response) {
    // 이미지 파일을 읽는다
    fs.readFile('sample.jpeg',function(errer, data){
        response.writeHead(200,{'Content-Type':'image/jpeg'});
        response.end(data);
    })
}).listen(52273, function(){
    console.log('Server Running at http://127.0.0.1:52273');
});

// 52274번 포트에 서버를 생성하고 실행ㅊ
http.createServer(function (request, response) {
    fs.readFile('GoodDay.mp3',function(err,data){
        response.writeHead(200,{'Content-Type':'audio/mp3'});
        response.end(data);
    })
}).listen(52274, function(){
    console.log('Server Running at http://127.0.0.1:52274');
});
