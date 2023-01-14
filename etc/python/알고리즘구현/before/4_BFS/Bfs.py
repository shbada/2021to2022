# BFS
from collections import deque


class Bfs:
    def __init__(self, graph, start):
        self.graph = graph
        self.start = start

    def bfs(self) -> list:
        visited = []
        queue = deque()  # deque : 파이썬 제공 객체 (스택/큐의 역할 가능)
        queue.append(self.start)

        while queue:
            node = queue.popleft()

            if node not in visited:
                visited.append(node)
                queue.extend(self.graph[node])

        return visited


graph = {
    'A': ['B'],
    'B': ['A', 'C', 'H'],
    'C': ['B', 'D'],
    'D': ['C', 'E', 'G'],
    'E': ['D', 'F'],
    'F': ['E'],
    'G': ['D'],
    'H': ['B', 'I', 'J', 'M'],
    'I': ['H'],
    'J': ['H', 'K'],
    'K': ['J', 'L'],
    'L': ['K'],
    'M': ['H']
}

bfs_obj = Bfs(graph, 'A')

result_list = bfs_obj.bfs()
print(result_list)

