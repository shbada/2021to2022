# 연결리스트가 팰린드롬인지 체크 (팰린드롬이란, 거꾸로해도 같은 문자)

# 입력 1-> 2 : false
# 입력 1->2->2->1 : true
import collections
from typing import Deque


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# 데큐 사용하기
def isPalindrome(self, head:ListNode) -> bool:
    # 데트 자료형 선언
    q: Deque = collections.deque()

    if not head:
        return True

    node = head

    while node is not None:
        q.append(node.val)
        node = node.next

    # 팰린드롬인지 판별
    while len(q) > 1:
        if q.popleft() != q.pop():
            return False

    return True


