// 모듈 추출
var http = require('http');

// 서버를 생성하고 실행
http.createServer(function(request, response){
    // writeHead() 메서드의 옵션 객체에 Set-Cookie 속성 값을 입력
    response.writeHead(200, {
        "Content-Type":'text/html',
        'Set-Cookie':['breakfast = toast', 'dinner=chicken'] // breakfast 쿠키 , dinner 쿠키
    });
}).listen(52273, function(){
    console.log('Server Running at http//127.0.0.1:52273')
});