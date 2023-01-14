# coding=utf-8

# keys : Key 리스트 만들기
a = {'name': 'pey', 'phone': '0119993323', 'birth': '1118'}
print(a.keys())  # dict_keys(['name', 'phone', 'birth'])

# list : 리스트 변환
print(list(a.keys()))  # ['name', 'phone', 'birth']

# values : value 리스트 만들
print(a.values())  # ['pey', '0119993323', '1118']

# items : key, value 쌍 얻기
print(a.items())  # [('phone', '0119993323'), ('name', 'pey'), ('birth', '1118')]

# clear : 지우기
a.clear()
print(a)  # {}

# get : key로 value 얻기
a = {'name':'pey', 'phone':'0119993323', 'birth': '1118'}
print(a.get('name'))  # 'pey'
print(a.get('phone'))  # '0119993323'
print(a.get('test'))  # 존재하지않음 : None
# print a['test'] # 존재하지않음 : 에러 발생

print(a.get('test', 'bar'))  # 'bar' (key가 존재하지않을 경우 default 지정 가능)

# in : key가 딕셔너리 안에 존재하는지 체크
a = {'name':'pey', 'phone':'0119993323', 'birth': '1118'}
print('name' in a)  # True
print('email' in a)  # False







