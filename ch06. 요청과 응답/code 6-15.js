// 모듈을 추출
var http = require('http');

// 웹 서버를 생성 및 실행
http.createServer(function(request, response){
    // Location 속성 : 웹페이지를 강제로 이동
    response.writeHead(302, {'Location':'http://www.hanbit.co.kr';});
    response.end();
}).listen(52273, function(){
    console.log('Server Running at http://127.0.0.1:52273';);
})