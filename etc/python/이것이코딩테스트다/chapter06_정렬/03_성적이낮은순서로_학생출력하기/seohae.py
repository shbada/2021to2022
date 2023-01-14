n = int(input())

array = []

for i in range(n):
    input_data = input().split()

    # 이름은 문자열
    # 점수는 정수형으로 변환하여 저장
    # 리스트안에 튜플 형태로 저장
    array.append((input_data[0], int(input_data[1])))

# 점수 오름차순
# 람다 사용
# 점수를 기준으로 정렬함을 명시
array = sorted(array, key=lambda student: student[1])
print(array)

# 출력은 이름으로
for student in array:
    print(student[0], end=' ')

