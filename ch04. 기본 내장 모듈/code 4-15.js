// 모듈을 추출
var fs = require('fs');

// 모듈을 사용
// 파일을 정상적으로 읽으면 콜백 함수의 두 번째 매개변수에 읽은 데이터를 전달합니다.
fs.readFile('4/4-14.Folder/textfile.txt','utf8',function(error, data){
    console.log(data);
});