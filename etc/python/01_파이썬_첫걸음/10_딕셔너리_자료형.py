# coding=utf-8

# 딕셔너리는 Key와 Value를 한 쌍으로 갖는 자료형
# 딕셔너리는 리스트나 튜플처럼 순차적으로(sequential) 해당 요솟값을 구하지 않고 Key를 통해 Value를 얻는다.

# 딕셔너리 기본
dic = {'name':'pey', 'phone':'0119993323', 'birth': '1118'}
print(dic)

a = {1: 'hi'}
print(a)

b = { 'a': [1,2,3]}
print(b)

# 딕셔너리 쌍 추가하기
a = {1: 'a'}
a[2] = 'b' # key:2, value:b
print(a)  # {1: 'a', 2: 'b'}

a['name'] = 'pey' # key:name, value:pey
print(a)  # {1: 'a', 2: 'b', 'name': 'pey'}

a[3] = [1,2,3] # key:3, value:[1,2,3]
print(a)  # {1: 'a', 2: 'b', 'name': 'pey', 3: [1, 2, 3]}

# 딕셔너리 요소 삭제하기
del a[1] # key:1인 요소 삭
print(a)  # {2: 'b', 'name': 'pey', 3: [1, 2, 3]}제

# 딕셔너리 value 얻기
grade = {'pey': 10, 'julliet': 99}
print(grade['pey'])# 10
print(grade['julliet'])# 99

a = {1:'a', 2:'b'}
print(a[1])  # 'a' (key:1인 요소)
print(a[2])  # 'b' (key:2인 요소)

dic = {'name':'pey', 'phone':'0119993323', 'birth': '1118'}
print(dic['name'])# 'pey'
print(dic['phone'])# '0119993323'
print(dic['birth'])# '1118'

# 딕셔너리에서 Key는 고유한 값이므로 중복되는 Key 값을 설정해 놓으면 하나를 제외한 나머지 것들이 모두 무시된다
a = {1:'a', 1:'b'}
print(a)  # {1: 'b'}
