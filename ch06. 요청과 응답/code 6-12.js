// 모듈 추출
var http = require('http');

// 서버를 생성하고 실행
http.createServer(function(request, response){
}).listen(52273, function(){
    console.log('Server Running at http://127.0.0.1:52273');
});