// 모듈을 추출
var http = require('http');
var url = require('url');

// 모듈을 사용
http.createServer(function(request, response){
    // 요청 내개변수를 추출
    var query = url.parse(request.url, true).query;

    // GET 요청 매개변수 출력
    response.writeHead(200, {"Content-Type":'text/html'});
    response.end('<h1>'+JSON.stringify(query)+'</h1>');
}).listen(52273, function(){
    console.log(`Server Running at http://127.0.0.1:52273`);
})