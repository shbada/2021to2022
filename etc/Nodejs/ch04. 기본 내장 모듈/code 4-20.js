// 모듈을 추출
var fs = require('fs');

// 파일을 읽는다
fs.readFile('4/4-14.Folder/textfile.txt','utf8',function(err,data){
    // 오류가 발생하면 곧바로 리턴
    if(err){return console.log(err);}
    // 원하는 처리
    console.log(data);
});

// 파일을 쓴다
fs.writeFile('4/4-14.Folder/textfile.txt','Hello Node 4-20 Update', function(err){
    // 오류가 발생하면 곧바로 리턴
    if(err){
        return console.log(err)
    }
    // 원하는 처리
    console.log('File Write Complite');
});