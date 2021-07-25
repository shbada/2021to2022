# 연결리스트가 팰린드롬인지 체크 (팰린드롬이란, 거꾸로해도 같은 문자)

# 입력 1-> 2 : false
# 입력 1->2->2->1 : true
from typing import List


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


# 리스트 변환
def isPalindrome(self, head: ListNode) -> bool:
    # 노드가 1개일 경우 - 팰린드롬
    if head.next is None:
        return True

    list = []

    # 연결리스트를 리스트로 변환
    while head is not None:
        # value append
        list.append(head.val)

        # 다음 노드의 값을 저장
        head = head.next

        print(list)

    # 리스트와 역순리스트 비교
    if list == list[::-1]:
        return True
    else:
        return False





