"""
defaultdict 객체 : 존재하지 않는 키를 조회할 경우, 에러 메시지를 출력하는 대신 디폴트 값을 기준으로 해당 키에 대한 딕셔너리 아이템을 생성한다.
"""
import collections

a = collections.defaultdict(int)
a['A'] = 5
a['B'] = 4

print(a) # defaultdict(<class 'int'>, {'A': 5, 'B': 4})

a['C'] += 1 # 존재하지않는 key 사용
print(a) # defaultdict(<class 'int'>, {'A': 5, 'B': 4, 'C': 1})

# default(0) 을 기준으로 자동으로 생성한 후 1을 더해 key 'C'가 생성되었다.


"""
Counter 객체 : 아이템에 대한 개수를 계산하여 딕셔너리로 리턴한다.
"""

a = [1, 2, 3, 4, 5, 5]
b = collections.Counter(a)
print(b) # Counter({5: 2, 1: 1, 2: 1, 3: 1, 4: 1})

# 해당 아이템의 개수가 들어간 딕셔너리를 생성해준다.
# 한번 더 딕셔너리를 래핑한 collections.Counter 클래스를 갖는다.

print(type(b)) # <class 'collections.Counter'>

print(b.most_common(2)) # 가장 빈도수가 높은 요소를 추출 (2개의 요소를 추출) ; [(5, 2), (1, 1)]


"""
OrderedDict 객체 : 해시 테이블을 이용한 자료형은 순서가 유지되지 않기 때문에, 입력 순서가 유지되는 별도의 객체를 사용한다.
"""

collections.OrderedDict({'key1': 1, 'key2': 2, 'key3': 3})




