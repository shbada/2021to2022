/*

웹 브라우저에서 동작하는 자바스크립트의 최상위 객체 : window
- window 객체는 웹 브라우저 자체와 관련된 속성과 메서드를 갖고있다.
- 하지만, Node.js는 웹 브라우저에서 동작하는 것이 아니므로 window 객체가 존재하지 않는다.
- 대신, 전역변수 / 전역함수를 갖는다.

*/

//  <문자열 자료형의 전역변수>

console.log('filename: ', __filename);
console.log('dirname: ', __dirname);