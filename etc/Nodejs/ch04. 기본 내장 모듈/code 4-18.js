// 모듈을 추출
var fs = require('fs');

// 파일을 읽는다
fs.readFile('4/4-14.Folder/textfile.txt','utf8',function(err, data){
    if(err){
        console.log(err);
    } else {
        console.log(data);
    }
});

// 파일을 쓴다
fs.writeFile('4/4-14.Folder/textfile.txt','Hello Node Update Async','utf8',function(err, data){
    if(err){
        console.log(err);
    } else {
        console.log('File Write')
    }
});