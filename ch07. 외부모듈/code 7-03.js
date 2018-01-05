// 모듈을 추출
var http = require('http');
var fs = require('fs');

// 서버를 생성하고 실행
http.createServer(function(request, response){
    // ejsPagge.ejs 파일을 읽음
    fs.readFile('7-04.ejs','utf8',function(err,data){
    });
}).listen(52273,function(){
    console.log('Server Running at http://127.0.0.1:62273');
})