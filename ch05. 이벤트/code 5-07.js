// 변수 선언
var onUncaughtException = function(err){
    console.log('예외 발생');
};

// process 객체에 uncayghtException 이벤트 연결
process.on('uncaughtException', onUncaughtException);

// 2초 간격으로 예외 발생
var test = function(){
    setTimeout(test,2000);
    error.error.error();
};

setTimeout(test,2000);