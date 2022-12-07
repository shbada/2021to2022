# BFS 메서드 정의
from collections import deque


def bfs(graph, start, visited):
    # 큐 구현을 위해 deque 라이브러리 사용
    queue = deque([start])

    # 현재 노드를 방문 처리
    visited[start] = True

    # 큐가 빌때까지 반복
    while queue:
        # 큐에서 하나의 원소를 뽑아 출력
        v = queue.popleft()
        print(v, end=' ')

        # 해당 원소와 연결된 아직 방문하지 않은 원소들을 큐에 삽입
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True

# 각 노드가 연결된 정보를 리스트 자료형으로 표현
graph = [
    [],
    [2, 3, 8],  # 1번 노드의 인접 노드는 2, 3, 8 이다.
    [1, 7],     # 2번 노드의 인접 노드는 1, 7 이다.
    [1, 4, 5],  # 3번 노드의 인접 노드는 1, 4, 5 이다.
    [3, 5],     # 4번 노드의 인접 노드는 4, 5 이다.
    [3, 4],     # 5번 노드의 인접 노드는 3, 4 이다.
    [7],        # 6번 노드의 인접 노드는 7 이다.
    [2, 6, 8],  # 7번 노드의 인접 노드는 2, 6, 8 이다.
    [1, 7]      # 8번 노드의 인접 노드는 1, 7 이다.
]

# 각 노드가 방문된 정보를 리스트 자료형으로 표현
visited = [False] * 9  # [False, False, False, False, False, False, False, False, False]

# BFS 함수 호출
bfs(graph, 1, visited)