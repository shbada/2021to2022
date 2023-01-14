# 정렬되어있는 두 연결 리스트를 합쳐라
# 입력 1->2->4, 1->3->4
# 출력 1->1->2->3->4->4

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


# 리스트 변환
def mergeTwoLists(l1: ListNode, l2: ListNode) -> ListNode:

    while l1 is not None:
        prev = l1.val

        while l2 is not None:
            if prev >= l2.val:
                # l1이 기존에 가르키고있던 노드 저장
                prevL1 = l1.next

                # l1이 가리키는 노드를 l2로 변경
                l1.next = l2

                # 기존에 l2가 가리키던 노드를 저장
                prevL2 = l2.next

                # l2가 가리키는 노드를 l1 다음 노드로 변경
                l1.next.next = prevL1

                # l2는 기존 l2가 가리키던 노드로 변경
                l2 = prevL2
            else:
                # l2 다음 노드로 변경
                l2 = l2.next

        l1 = l1.next

    return l1


l1 = ListNode(1)
l1.next = ListNode(2)
l1.next.next = ListNode(4)

l2 = ListNode(1)
l2.next = ListNode(3)
l2.next.next = ListNode(4)

answer = mergeTwoLists(l1, l2)
