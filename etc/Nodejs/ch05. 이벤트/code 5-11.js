// emit() 메서드를 사용하면 exit 이벤트를 강제로 발생시킬 수 있다

// exit 이벤트 연결
process.on('exit', function(code){
    console.log('BYE');
});

process.emit('exit');
process.emit('exit');
process.emit('exit');
process.emit('exit');

// 프로그램 실행 중
console.log('프로그램이 실행중입니다');

// exit 이벤트를 강제로 호출해도 프로그램이 종료되지 않고,
// emit() 메서드를 사용해 이벤트를 강제로 호출하면 이벤트 리스터만 실행된다.