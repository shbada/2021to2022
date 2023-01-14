# 연결리스트를 입력받아 페어(pair) 단위로 스왑하라

# 입력 1->2->3->4
# 출력 2->1->4->3

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


def swapPairs(self, head: ListNode) -> ListNode:
    cur = head

    while cur and cur.next:
        # 값 교환
        cur.val, cur.next.val = cur.next.val, cur.var
        cur = cur.next.next

    return head
