//  프로그램 실행 시간 측정

console.time('alpha'); //시작

var output = 1;
for (var i = 1; i<=10; i++){
    output *= i;
}

console.log('Result', output);

console.timeEnd('alpha'); // 종료