// 모듈을 추출
var crypto = require('crypto');

// 변수를 선언
var key = "아무도 알지 목하는 나만의 비밀 키";
var input = "PASSWORD";

// 암호화
var cipher = crypto.createCipher('aes192',key);
cipher.update(input, 'utf8', 'base64');
var cipheredOutput = cipher.final('base64');

// 암호화 해제
var decipher = crypto.createDecipher('aes192',key);
decipher.update(cipheredOutput, 'base64', 'utf8');
var decipherOutput = decipher.final('utf8');

// 출력합니다
console.log(input);
console.log(cipheredOutput);
console.log(decipherOutput);