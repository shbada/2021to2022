# concurrency-mileage-project
[동시성 제어] 프로젝트로 동시성제어 공부하기

### 강의 github
- https://github.com/seohaebada/concurrency-stock
  - 위 내용을 프로젝트로 실습해보는 과정

### package
1) mileage
- 포인트 충전
- 포인트 사용
2) mileage-history
- 포인트 충전 내역
- 포인트 사용 내역

### TEST 목록
1) 포인트_충전을_동시에_여러건_처리한다()
- 동시성 제어 테스트

2) 포인트_사용을_동시에_여러건_처리한다()
- 동시성 제어 테스트

3) 동기방식_포인트_조회한다()
- 2개의 외부 API 호출
  - callItemTest() : 1초 수행
  - callMemberTest() : 2초 수행 
- 동기 수행 -> 3초 수행

4) 비동기방식_포인트_조회한다()
- 2개의 외부 API 호출
  - callItemTest() : 1초 수행
  - callMemberTest() : 2초 수행 
- 2개의 외부 API를 동시에 비동기로 호출 -> 총 1 ~ 2초 수행
