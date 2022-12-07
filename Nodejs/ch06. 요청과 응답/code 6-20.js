// 모듈을 추출
var http = require('http');
var fs = require('fs');
var url = require('url');

// 서버를 생성 및 실행
http.createServer(function (request, response){
    // 변수를 선언
    var pathname = url.parse(request.url).pathname;
    // 페이지를 구분
    if ( pathname == '/' ) {
    
    } else if( pathname == '/OtherPage' ) {
    
    }
}).listen(52273, function(){
    console.log('Server Running at http://127.0.0.1:52273';);
})
