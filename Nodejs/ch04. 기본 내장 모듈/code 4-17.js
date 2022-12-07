// 동기처리를 하는 메서드의 예외처리
// 모듈 추출
var fs = require('fs');

// 파일을 읽는다
try{
    var data = fs.readFileSync('4/4-14.Folder/textfile.txt','utf8');
} catch(e){
    console.log(e);
};

// 파일을 쓴다
try{
    fs.writeFileSync('4/4-14.Folder/textfile.txt','Hello Node Update..!','utf8');
    console.log('File Write Complite');
}catch(e){
    console.log(e);
}