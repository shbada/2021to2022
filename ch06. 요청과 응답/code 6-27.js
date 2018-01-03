// 모듈을 추출
var http = require('http');

// 모듈을 사용
http.createServer(function (request, response){
    // GET COOKIE
    var cookie = request.headers.cookie; // 쿠키 속성: 문자열

    // SET COOKIE
    response.writeHead(200, {
        "Content-Type":"text/html",
        "Set-Cookie":['name=RintIanTta','region=Seoul']
    });

    // 응답
    response.end('<h1>'+JSON.stringify(cookie)+'</h1>');
}).listen(52273, function(){
    console.log('Server Running at http://127.0.0.1:52273');
})