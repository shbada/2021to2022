# 연결리스트

class Node:
    def __init__(self, data):
        self.data = data
        self.next = None


def add(self, node):
    # 다음 노드가 None 이라면,
    if self.next is None:
        self.next = node
    # 다음 노드가 None이 아니라면,
    else:
        n = self.next

        # 마지막 노드를 찾을때까지 반복문 실행
        while True:
            # 마지막 노드를 찾았다면, 해당 노드의 다음 노드는 None 일 것
            if n.next is None:
                n.next = node
                break
            else:  # 마지막 노드가 아니라면 계속해서 다음 노드 탐색
                n = n.next


def select(self, idx):
    n = self.next

    for i in range(idx - 1):
        n = n.next

    return n.data


def delete(self, idx):
    # 다음 노드를 저장
    n = self.next  # 마지막 노드라면 None


    # 마지막 노드는 제외 (다음 노드가 없으므로)
    for i in range(idx - 2):
        n = n.next

    t = n.next
    n.next = t.next

    del t

