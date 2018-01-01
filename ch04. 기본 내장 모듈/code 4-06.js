// 모듈을 추출
var url = require('url');
var querystring = require('querystring');

// 모듈을 사용
// url 모듈을 사용하여 URL 문자열을 분해한 후,
var parseObject = url.parse('http://www.hanbit.co.kr/store/books/look.php?p_code=B425027160');
// Query String 모듈로 쿼리 부분을 분해한다
console.log(querystring.parse(parseObject.query));