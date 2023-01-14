# DFS
class Dfs:
    def __init__(self, graph, start):
        self.graph = graph
        self.start = start

    def dfs(self) -> list:
        visited = []
        stack = [self.start]

        while stack:
            node = stack.pop()

            if node not in visited:
                visited.append(node)

                # reverse : 왼쪽 먼저
                stack.extend(reversed(self.graph[node]))

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

dfs_obj = Dfs(graph, 'A')

result_list = dfs_obj.dfs()
print(result_list)

