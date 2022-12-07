package com.algorithm._00_알고리즘_구현.youtube1;

/*
BFS
- 그래프 탐색 : 어떤 것들이 연속해서 이어질때 모두 확인하는 방법이다.
  (Graph : Vertex(어떤것) + Edge(이어지는것))
- Breadth-first search (너비 우선 탐색)
- 자기 자식을 우선으로 탐색한다.

[아이디어]
- 시작점에 연결된 Vertex 찾기
- 찾은 Vertex 를 Queue 에 저장 (Queue : 들어온 순서대로 나간다.)
- Queue 의 가장 먼저것을 뽑아서 반복

[시간복잡도]
O(V+E)
- Vertex, Edge 모두 다 계산되기 때문

[자료구조]
- 검색할 그래프
- 방문 여부 확인 (재방문 금지)
- Queue : BFS    실행
 */
public class _01_BFS {
}
