package _18_코틀린에서_컬렉션을_함수로_다루는_방법

/*
1) all : 조건을 모두 만족하면 true, 그렇지 않으면 false
val isAllApple = fruits.all { fruit -> fruit.name == "사과 }

2) none : 조건을 모두 불만족하면 true, 그렇지 않으면 false
3) any : 조건을 하나라도 만족하면 ture, 그렇지 않으면 false
3) count : 총 개수를 센다
4) sortedBy : (오름차순) 정렬을 한다
5) sortedByDescending : (내림차순) 정렬을 한다
6) distinctBy : 변형된 값을 기준으로 중복을 제거한다.
val a = fruits.distinctBy { fruit -> fruit.name } // name 중복 제거
7) first : 첫번째 값을 가져온다 (무조건 null이 아니어야함)
8) firstOrNull : 첫번째 값 또는 null을 가져온다
9) last : 마지막 값을 가져온다. (무조건 null이 아니어야함)
10) lastOrNull : 마지막 값 또는 null을 가져온다
11) groupBy : List -> Map 전환
val map = Map<String, List<Future>> = fruits.groupBy { fruit -> fruit.name} // 이름 기준으로 그룹핑
12) associateBy : id (key), 과일 (value)인 Map이 필요한 경우
val map: Map<Long, Fruit> = fruits.associateBy { fruit -> fruit.id } // id를 통해서 뭔가를 매핑해야할때
13) key, value 동시에 처리
val map: Map<String, List<Long>> = fruits.groupBy( {fruit -> fruit.name}, {fruit -> fruit.factoryPrice})
14) id -> 출고가 Map
mal map = Map<Long, Long> = fruits.associateBy({fruit -> fruit.id}, {fruit -> fruit.factoryPrice})
15) Map Filter
val map: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }.filter { (key, value) -> key == "사과" }
 */