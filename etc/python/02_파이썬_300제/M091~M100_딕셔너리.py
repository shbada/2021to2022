# 091. 이름을 key로, (가격,재고)를 value로 딕셔너리를 구성하세요.
inventory = {"메로나": [300, 20], "비비빅": [400, 3], "죠스바": [250, 100]}
print(inventory)

# 092. 딕셔너리에서 메로나의 가격을 출력하세요
print(inventory["메로나"][0])

# 093. 딕셔너리에서 메로나의 재고를 출력하세요
print(inventory["메로나"][1])

# 094. 딕셔너리에 값을 추가하세요
inventory["월드콘"] = [500, 7]
print(inventory)

# 095. 딕셔너리로부터 key 값으로만 구성된 리스트를 만드세요
icecream = inventory.keys()
print(icecream)
print(list(icecream))

# 096. 딕셔너리로부터 value 값으로만 구성된 리스트를 만드세요
icecream = inventory.values()
print(icecream)
print(list(icecream))

# 097. 딕셔너리에서 가격의 총 합을 출력하세요
inventory = {"메로나": 1200, "비비빅": 1300, "죠스바": 1500}
print(sum(inventory.values()))

# 098. 2개의 딕셔너리를 합친 딕셔너리를 생성하세요
inventory = {"메로나": 1200, "비비빅": 1300, "죠스바": 1500}
new_inventory = {"슈크림": 1200, "빵빠레": 1800}
inventory.update(new_inventory)
print(inventory)  # {'메로나': 1200, '비비빅': 1300, '죠스바': 1500, '슈크림': 1200, '빵빠레': 1800}

# 099. 두개의 튜플을 하나의 딕셔너리로 변환하세요. (keys를 키로, vals를 값으로)
keys = ("apple", "pear", "peach")
vals = (300, 200, 100)
result = dict(zip(keys, vals))  # {'apple': 300, 'pear': 200, 'peach': 100}
print(result)

# 100. 두개의 리스트를 하나의 딕셔너리로 변환하세요. (keys를 키로, vals를 값으로)
date = ['09/05', '09/06', '09/07']
close_price = [1000, 2000, 3000, 4000]
result = dict(zip(date, close_price))  # {'09/05': 1000, '09/06': 2000, '09/07': 3000}
print(result)  # key, value가 매칭이 맞지 않아도 매칭 되는것끼리 생성한다
