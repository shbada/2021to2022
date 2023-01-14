// 이벤트를 연결할 수 있는 모든 객체는 EventEmitter 객체의 상속을 받는다

const EventEmitter = require('events');

// EventEmitter 객체를 생성합니다.
var custom = new EventEmitter();