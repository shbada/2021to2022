# 사전 자료형
# 키(Key), 값(Value)의 쌍을 데이터로 가지는 자료형
# 키와 값을 쌍으로 데이터를 가지며, 변경 불가능한(Immutable) 자료형을 키(Key)로 사용한다.


data = dict()
data['사과'] = 'Apple'
data['바나나'] = 'Banana'
data['코코넛'] = 'Coconut'

print(data)  # {'사과': 'Apple', '바나나': 'Banana', '코코넛': 'Coconut'}

if '사과' in data:
    print("'사과'를 키로 가지는 데이터가 존재한다.")


# 키 데이터만 뽑고자 한다. => keys()
# 값 데이터만 뽑고자 한다. => values()

key_list = data.keys()
value_list = data.values()

print(key_list)  # dict_keys(['사과', '바나나', '코코넛'])
print(value_list)  # dict_values(['Apple', 'Banana', 'Coconut'])


b = {
    '홍길동': 97,
    '김테스': 98
}

print(b)

print(b['김테스'])  # 특정 key로 value 얻기