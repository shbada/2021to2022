# 051. 리스트를 생성하세요
list = ["닥터 스트레인지", "스플릿", "럭키"]
print(list)

# 052. 리스트에 원소 추가하기
list.append("써니")
print(list)

# 053. "슈퍼맨"을 두번째 원소 위치에 추가하세요
list.insert(1, "슈퍼맨")
print(list)

# 054. 리스트에서 "럭키"를 삭제하세요
del list[3]
print(list)

# 055. "스플릿"과 "써니"를 삭제하라
del list[2]
print(list)  # 리스트에서 어떤 값을 삭제하면 남은 값들은 새로 인덱싱된다. 여러 값을 삭제할때에는 삭제 후의 리스트를 고려해야한다.

del list[2]
print(list)

# 056. lan1, lan2 두개의 리스트가 있을때 두개 리스트의 원소를 모두 갖는 리스트를 만드세요
lang1 = ["C", "C++", "JAVA"]
lang2 = ["Python", "Go", "C#"]

lang3 = lang1 + lang2
print(lang3)

# 057. 리스트에서 최솟값과 최대값을 출력하세요
nums = [1, 2, 3, 4, 5, 6, 7]
print(max(nums))
print(min(nums))

# 058. 리스트의 합을 출력하세요
nums = [1, 2, 3, 4, 5]
print(sum(nums))

# 059. 리스트에 저장된 데이터의 개수를 구하세요
nums = [1, 2, 3, 4, 5, 6, 7, 8]
print(len(nums))

# 060. 리스트의 평균을 출력하세요
nums = [1, 2, 3, 4, 5]
print(sum(nums) / len(nums))  # 3.0