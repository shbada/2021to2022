// 모듈을 추출
var crypto = require('crypto');

// 해시를 생성
// 해시 값은 원래 상태로 돌리지 못한다
var shasum = crypto.createHash('sha256');
shasum.update('crypto_hash');
var output = shasum.digest('hex');

// 출력
console.log('crypto_hash:', output);