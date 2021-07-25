# 런너를 사용한 풀이

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def isPalindrome(self, head: ListNode) -> bool:
    rev = None
    # 빠른 런너, 느린 런너의 초기값은 모두 head 에서 시작한다.
    slow = fast = head

    # 런너를 이용해 역순 연결 리스트 구성
    while fast and fast.next:  # next 가 존재하지 않을때까지
        fast = fast.next.next  # fast를 이동시킨다. (빠른 런너는 2칸씩 이동한다.)

        # rev : 연결 리스트를 역순으로 저장하는 변수
        # rev 는 처음 None 으로 시작하고, 1 -> None, 2->1->None 처럼 점점 이전 값으로 연결되는 구조이다.
        # rev 는 앞에 계속 새로운 노드가 추가되고 있다.
        # 결국 rev 는 slow 의 역순 연결 리스트가 된다.
        rev, rev.next, slow = slow, rev, slow.next  # (느린 런너는 1칸씩 이동한다.)

    # 입력값이 홀수일때와 짝수일때 마지막 처리가 다르다.
    # 홀수일때는 slow 런너가 한칸 더 앞으로 이동하여 중앙의 값을 빗겨가야 한다.
    # 3은 중앙에 위치한 값으로 팰린드롬 체크에서 배제되어야 하기 때문이다. [1, 2, 3, 2, 1]
    # fast 가 None 이 아니라는 경우로 간주할 수 있다. -> slow 를 한칸 더 이동하여 마무리한다.
    if fast:
        slow = slow.next

    # 팰린드롬 여부 확인
    while rev and rev.val == slow.val:
        slow, rev = slow.next, rev.next
    return not rev
