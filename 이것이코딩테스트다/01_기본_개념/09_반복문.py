# 반복문
# 특정한 소스코드를 반복적으로 실행하고자 할때 사용하는 문법

# while 문
i = 1
result = 0

while i <= 9:  # i 가 9보다 작거나 같을때 아래 코드를 반복적으로 실행
        result += i
        i += 1

print(result)


# for 문
# 원소를 첫번째 인덱스부터 차례대로 하나씩 방문한다.
"""
for 변수 i 리스트:
    실행할 소스코드
"""

# continue
result = 0

for i in range(1, 10):
    if i % 2 == 0:
        continue  # 해당 조건을 만족한다면 건너뛴다.
    result += i

print(result)


# break
# 반복문을 즉시 탈출

i = 1
while True:
    print("현재의 값 : ", i)

    if i == 5:
        break  # i가 5부터는 반복문을 탈출한다.
    i += 1