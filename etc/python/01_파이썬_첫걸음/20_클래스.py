# coding=utf-8

# 클래스
class Calculator:
    def __init__(self):
        self.result = 0

    def add(self, num):
        self.result += num
        return self.result

cal1 = Calculator()
cal2 = Calculator()

print(cal1.add(3))
print(cal1.add(4))
print(cal2.add(3))
print(cal2.add(7))

class FourCal:
    pass
print(type(FourCal)) # <type 'classobj'>

# self
class FourCal:
    def setdata(self, first, second):
        self.first = first # self : 아래 a의 first (= a.first = 4)
        self.second = second # (=a.second = 2)

a = FourCal()
a.setdata(4, 2)

print(a.first) # 4
print(a.second) #

# self
class FourCal:
    def setdata(self, first, second):
        self.first = first
        self.second = second
    def add(self):
        result = self.first + self.second
        return result

a = FourCal()
a.setdata(4, 2)
print(a.add()) # 6

# 기본 덧셈/곱셈/나눗셈 구현
class FourCal:
    def setdata(self, first, second):
        self.first = first
        self.second = second
    def add(self):
        result = self.first + self.second
        return result
    def mul(self):
        result = self.first * self.second
        return result
    def sub(self):
        result = self.first - self.second
        return result
    def div(self):
        result = self.first / self.second
        return result

# 생성자
class FourCal:
    # 이름 __init__ 메소드 : 생성자로 인식되어 객체가 생성되는 시점에 자동으로 호출한다.
    def __init__(self, first, second):
        self.first = first
        self.second = second




