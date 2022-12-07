# 071. 튜플을 만들어라
my_variable = ()
print(type(my_variable))

# 072. 요소 3개인 튜플을 만드세요
movies = ("닥터 스트레인지", "스플릿", "럭키")
print(movies)

# 073. 숫자 1이 저장된 튜플을 생성하세요
my_tuple = (1)
print(type(my_tuple))  # <class 'int'> (튜플이 아닌 정수형으로 인식한다)

my_tuple = (1, )
print(type(my_tuple))  # <class 'tuple'> 쉼표를 함께 입력하자.

# 074. 에러 발생의 이유는?
t = (1, 2, 3)
# t[0] = 'a' > 튜플은 원소의 값을 변경할 수 없다.

# 075. t = 1, 2, 3, 4 의 타입은? 튜플
t = 1, 2, 3
print(type(t))  # <class 'tuple'> (괄호 생략이 가능하다)

# 076. 튜플을 업데이트 하세요
t = ('a', 'b', 'c')
t = ('A', 'b', 'c')
print(t)  # 기존의 튜플을 삭제하고 마지막으로 저장된 튜플이 출력된다

# 077. 튜플을 리스트로 변환하세요
interest = ('삼성전자', 'LG전자', '한화')
list_a = list(interest)
print(list_a)

# 078. 리스트를 튜플로 변환하세요
list_a = ['삼성전자', 'LG전자', '한화']
tuple_b = tuple(list_a)
print(tuple_b)

# 079. 튜플 언팩
temp = ('apple', 'banana', 'cake')
a, b, c = temp
print(a, b, c)  # apple banana cake

# 080. 1~99 정수 중 짝수만 저장된 튜플을 생성하라
data = tuple(range(2, 100, 2))
print(data)
