// EventEmitter 객체를 생성
var EventEmitter = require('events');
var custom = new EventEmitter();

// 이벤트를 연결
custom.on('tick', function (code) {
    console.log('이벤트 실행!');
});

custom.emit('tick');