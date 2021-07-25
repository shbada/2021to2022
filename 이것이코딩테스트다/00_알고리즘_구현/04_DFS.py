# DFS 메서드 정의
def dfs(graph, v, visited):
    # 현재 노드 방문 처리
    visited[v] = True
    print(v, end=' ')

    # 현재 노드와 연결된 다른 노드를 재귀적으로 방문
    for i in graph[v]:
        if not visited[i]:
            dfs(graph, i, visited)


# 각 노드가 연결된 정보를 리스트 자료형으로 표현
graph = [
    [],  # 인덱스를 1번부터 시작하기 위해 0은 빈리스트로 둔다.
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
visited = [False] * 9  # [False, False, False, False, False, False, False, False, False]  1부터 시작하므로 (+1을 해서 9)

# DFS 함수 호출
dfs(graph, 1, visited)


