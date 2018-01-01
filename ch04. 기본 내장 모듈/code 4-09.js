// 모듈을 추출
var util = require('util');

// 모듈을 사용
// format() 메소드를 사용하여 문자열을 조합하고 출력한다
var data = util.format('%d + %d = %d', 52, 273, 52+273);
console.log(data);