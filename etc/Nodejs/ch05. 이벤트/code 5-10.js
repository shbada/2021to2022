// exit 이벤트 연결
process.on('exit', function(code){
    console.log('BYE');
});

// emit() 메서드를 사용하면 exit 이벤트를 강제로 발생시킬 수 있다 -> code 5-11.js