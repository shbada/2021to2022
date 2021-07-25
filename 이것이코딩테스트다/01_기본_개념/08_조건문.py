# 조건문 : 프로그램의 흐름을 제어하는 문법
# 파이썬은 코드의 블록을 들여쓰기(Indent)로 지정한다. (들여쓰기 표준: 스페이스바 4개)

# if~elif~else

# 파이썬에서 논리연산자 = and, or, not 단어 사용
# X and Y : X, Y 모두가 true 일때 true
# X or Y : X, Y 중 하나라도 true 일때 true
# not X : x가 false 일때 true

# 파이썬에서 아무것도 처리하고싶지 않을 때 pass 키워드를 사용한다.

source = 85
if source >= 80:
    pass  # 나중에 작성할 코드
else:
    print("성적이 80점 미만입니다.")


# 수학의 부등식을 그대로 사용 가능
x = 15
if 0 < x < 20:  # 타 개발언어: 0 < x && x < 20
    pass

