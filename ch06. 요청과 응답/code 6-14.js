// 모듈 생성
var http = require('http');

// 서버를 생성하고 실행
http.createServer((req, res) => {
    // 변수 선언
    var date = new Date();
    date.setDate(date.getDate() + 7);
    
    // 쿠키 입력
    res.writeHead(200, {
        'Conetnet-Type':'text/html',
        'Set-Cookie':[
            'breakfast = toast:Expires = '+date.toUTCString(),
            'dinner = chicken'
        ]
    })
    
    // 쿠키 출력
    res.end('<h1>'+req.headers.cookie+'</h1>');
}).listen(52273, () => {
    console.log('Server Running at http://127.0.0.1:52273');
});