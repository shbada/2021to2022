// 모듈을 추출
var http = require('http');
var fs = require('fs');

// 모듈을 사용
http.createServer(function(request, response) {

    if(request.method == 'GET') {
        // GET 요청
        fs.readFile('6-25.html',function(err,data){
            response.writeHead(200, {"Content-Type" : 'text/html'});
            response.end(data);
        });
    } else if(request.method == 'POST') {
        // POST 요청
        request.on('data',function(data){
            response.writeHead(200, {'Contet-Type':'text/html'});
            response.end('<h1>'+data+'</h1>');
        });
    }
}).listen(52273, function(){
    console.log(`Server Running at http://127.0.0.1:52273`);
});