// 모듈을 추출
var http = require('http');

// 모듈을 사용
http.createServer(function(request, response){
    // 쿠키가 있는지 확인
    if(request.headers.cookie){
        // 쿠키를 추출하고 분해
        var cookie = request.headers.cookie.split(';').map(function(element){
            var element = element.split('=');
            return {
                key:element[0],
                value:element[1]
            };
        });

        // 응답
        response.end('<h1>'+JSON.stringify(cookie)+'</h1>');
    } else {
        // 쿠키를 생성
        response.writeHead(200, {
            'Content-Type':'text/html',
            'Set-Cookie':['name=RintIamTta','regeion=Seoul;Expires='+new Date()]
        });
        response.end('<h1>쿠키를 생성합니다,</h1>');
    }
}).listen(52273,function(){
    console.log('Server Running at http://127.0.0.1:52273');
});