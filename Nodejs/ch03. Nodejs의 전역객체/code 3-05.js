/*

process 객체 : 프로그램과 관련된 정보를 나타내는 객체
[[웹 브라우저에서 작동하는 자바스크립트에 존재하지 않는 Node.js만이 가진 객체]]

*/

//  <process.argv 속성과 process.exit() 메서드>

process.argv.forEach(function (item, index){
    console.log(index + ':'+typeof(item)+':', item);
    
    if(item == '--exit'){
        var exitTime = Number(process.argv[index + 1]);
        
        setTimeout(function(){
            process.exit();
        }, exitTime);
    }
});