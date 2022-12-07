// 모듈을 추출
var fs = require('fs');

// 모듈을 사용
// textfile.txt 파일에 입력한 데이터를 그대로 출력
var text = fs.readFileSync('4/4-14.Folder/textfile.txt','utf8');
console.log(text);