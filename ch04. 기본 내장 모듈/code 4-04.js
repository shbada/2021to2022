var url = require('url');

// 매개변수로 URL 문자열을 입력하면 URL을 분해해 객체로 만든다
var parseObject = url.parse('http://www.hanbit.co.kr/store/books/look.php?p_code=B4250257160');
console.log(parseObject);