// process 객체에 uncaughtException 이벤트를 연결
// once() : 이벤트 리스너를 한번만 연결
process.once('uncaughtException', function(err){
    console.log('예외발생');
});

// 2초 간격으로 예외를 발생
var test = function (){ㅊ
    setTimeout(test, 2000);
    error.error.error();
};

setTimeout(test, 2000);