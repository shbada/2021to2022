# BFS
from collections import deque


def bfs(graph, start, visited):
    # 큐 구현을 위해 deque 라이브러리 사용
    queue = deque([start])

    # 현재 노드를 방문 처리
    visited[start] = True

    while queue:
        v = queue.popleft()
        print(v, end=' ')

        for value in graph[v]:
            if not visited[value]:
                queue.append(value)
                visited[value] = True


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