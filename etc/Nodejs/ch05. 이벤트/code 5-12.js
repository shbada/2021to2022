// exit() 메서드를 사용해 프로그램을 강제로 종료

// exit 이벤트를 연결
process.on('exit',function (code) {
    console.log('BYE');
});

// 프로그램을 종료
process.exit();

// 이벤트를 강제로 발생
process.emit('exit');
process.emit('exit');
process.emit('exit');
process.emit('exit');

// 프로그램 실행 중C
console.log('프로그램 실행 중');