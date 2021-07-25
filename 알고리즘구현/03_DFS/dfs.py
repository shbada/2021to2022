# DFS
def dfs(graph, start, visited):
    visited[start] = True
    print(start, end=' ')

    for value in graph[start]:
        if not visited[value]:
            dfs(graph, value, visited)



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