# 251. 생략

# 252. 비어있는 사람 (Human) 클래스를 "정의" 해보세요.
class Human:
    pass


# 253. 사람 (Human) 클래스의 인스턴스를 "생성" 하고 이를 areum 변수로 바인딩해보세요.
areum = Human()


# 254. 사람 (Human) 클래스에 "응애응애"를 출력하는 생성자를 추가하세요.
class Human:
    def __init__(self):
        print("응애응애")


# 255. 사람 (Human) 클래스에 (이름, 나이, 성별)을 받는 생성자를 추가하세요.
class Human:
    def __init__(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex


areum = Human("아름", 25, "여자")
print(areum.name)


# 256. 255에서 생성한 인스턴스의 이름, 나이, 성별을 출력하세요. 인스턴스 변수에 접근하여 값을 출력하면 됩니다.
class Human:
    def __init__(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex


areum = Human("아름", 25, "여자")
print(areum.age)


# 257. 사람 (Human) 클래스에서 이름, 나이, 성별을 출력하는 who() 메소드를 추가하세요.
class Human:
    def __init__(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex

    def who(self):
        print("이름: {} 나이: {} 성별: {}".format(self.name, self.age, self.sex))


areum = Human("아름", 25, "여자")
areum.who()      # Human.who(areum)


# 258. 사람 (Human) 클래스에 (이름, 나이, 성별)을 받는 setInfo 메소드를 추가하세요.
class Human:
    def __init__(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex

    def who(self):
        print("이름: {} 나이: {} 성별: {}".format(self.name, self.age, self.sex))

    def setInfo(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex


areum = Human("불명", "미상", "모름")
areum.who()      # Human.who(areum)

areum.setInfo("아름", 25, "여자")
areum.who()      # Human.who(areum)


# 259. 사람 (human) 클래스에 "나의 죽음을 알리지 말라"를 출력하는 소멸자를 추가하세요.
class Human:
    def __init__(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex

    def __del__(self):
        print("나의 죽음을 알리지마라")

    def who(self):
        print("이름: {} 나이: {} 성별: {}".format(self.name, self.age, self.sex))

    def setInfo(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex


areum = Human("아름", 25, "여자")
del(areum)


# 260. 아래와 같은 에러가 발생한 원인에 대해 설명하세요.
class OMG:
    def print(self):
        print("Oh my god")

myStock = OMG()
myStock.print()  # OMG.print()
