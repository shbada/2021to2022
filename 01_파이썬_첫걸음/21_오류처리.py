# coding=utf-8

# try, except문
try:
    4 / 0
except ZeroDivisionError as e: # as 오류메시지변수
    print(e)

# try .. finally
f = open('foo.txt', 'w')
try:
    pass
finally:
    f.close() # 무조건 실행

try:
    a = [1,2]
    print(a[3])
    4/0
except ZeroDivisionError: # 오류 1
    print("0으로 나눌 수 없습니다.")
except IndexError: # 오류 2
    print("인덱싱 할 수 없습니다.")

# 여러개의 에러 처리
try:
    a = [1,2]
    print(a[3])
    4/0
except (ZeroDivisionError, IndexError) as e:
    print(e)

# 오류 회피
try:
    print("aaa")
except ZeroDivisionError:
    pass

# 오류 발생시키기
class Bird:
    def fly(self):
        raise NotImplementedError # 오류 발생시키기

# 에러 만들기
class MyError(Exception):
    def __str__(self): # rint문으로 출력할 경우에 호출되는 메서드
        return "허용되지 않는 별명입니다."

def say_nick(nick):
    if nick == '바보':
        raise MyError()
    print(nick)

# try~catch 에러 만들기
try:
    say_nick("천사")
    say_nick("바보")
except MyError as e:
    print(e) # 오류 메시지가 출력되지 않는다. -> __str__ 메서드 추가