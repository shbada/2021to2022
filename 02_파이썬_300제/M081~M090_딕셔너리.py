# 081. 10개의 리스트가 있을때 start expression을 사용하여 좌측 8개의 값을 변수에 바인딩하세요
sources = [8.8, 8.9, 8.7, 9.2, 9.7, 9.5, 7.8, 9.4]
*valid_scord, _, _ = sources  # valid_scord(나머지), 첫번째 _ (뒤에서 2번째), 두번째 _ (뒤에서 첫번째)
print(valid_scord)

# 082. 10개의 값이 저장된 리스트가 있을때 start expression을 사용하여 우측 8개의 값을 변수에 바인딩하세요
sources = [8.8, 8.9, 8.7, 9.2, 9.7, 9.5, 7.8, 9.4]
_, _, *valid_scord = sources
print(valid_scord)

# 083. 10개의 값이 저장된 리스트가 있을때 start expression을 사용하여 가운 8개의 값을 변수에 바인딩하세요
sources = [8.8, 8.9, 8.7, 9.2, 9.7, 9.5, 7.8, 9.4]
_, *valid_scord, _ = sources
print(valid_scord)

# 084. 비어있는 딕셔너리를 생성하세요
dic = {}

# 085. 딕셔너리를 구성하세요.
dic = {"a": "A", "b": "B", "c": "C"}
print(dic)

# 086. 딕셔너리에 원소를 추가하세요
dic = {"a": "A", "b": "B", "c": "C"}
dic["d"] = "D"
print(dic)

# 087. 딕셔너리를 사용하여 a의 value를 출력하세요
print(dic["a"])

# 088. 딕셔너리의 value를 수정하세요
dic["c"] = "CC"
print(dic)

# 089. 딕셔너리에서 key="c"인 원소를 삭제하세요
del dic["c"]
print(dic)

# 090. 에러가 발생한 원인은?
# print(dic["f"])  > 딕셔너리에 없는 키를 사용해서 인덱싱하면 에러가 발생한다.
