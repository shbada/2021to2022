// 모듈을 추출
var http = require('http');

// 모듈을 사용
http.createServer(function(request, response){
    request.on('data',function(data){
        console.log('POST Data :',data);
    });
}).listen(52273, function(){
    console.log(`Server Running at http://127.0.0.1:52273`);
})