# 연결리스트를 뒤집어라

# 1->2->3->4->5->NULL
# 5->4->3->2->1->NULL

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


def reverseList(head: ListNode, result: ListNode) -> ListNode:
    # 차례 노드 저장
    prev = head
    # 노드의 다음 노드를 현재 노드로 설정
    current_result = result
    result = head.next
    result.next = current_result

    return reverseList(prev.next, result)


l1 = ListNode(1)
l1.next = ListNode(2)
l1.next.next = ListNode(3)
l1.next.next.next = ListNode(4)
l1.next.next.next.mnext = ListNode(5)

resultNode = reverseList(l1, ListNode(None))

while resultNode is not None:
    print(f'{resultNode.val} ->', end=" ")
    answer = resultNode.next

