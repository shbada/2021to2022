//  code 5-07.js에서 이벤트 제거 추가

/// 변수 선언
var onUncaughtException = function(err){
    console.log('예외 발생');
    
    //이벤트 제거 ( 예외 발생 후, 프로그램 종료)
    process.removeListener('uncaughtException',onUncaughtException);
};

// process 객체에 uncayghtException 이벤트 연결
process.on('uncaughtException', onUncaughtException);

// 2초 간격으로 예외 발생
var test = function(){
    setTimeout(test,2000);
    error.error.error();
};

setTimeout(test,2000);