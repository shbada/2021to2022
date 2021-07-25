# 261. 주식 종목에 대한 정보를 저장하는 Stock 클래스를 정의해보세요.
class Stock:
    pass


# 262. Stock 클래스의 객체가 생성될 때 종목명과 종목코드를 입력 받을 수 있도록 생성자를 정의해보세요.
class Stock:
    def __init__(self, name, code):
        self.name = name
        self.code = code


삼성 = Stock("삼성전자", "005930")
print(삼성.name)
print(삼성.code)


# 263. 객체에 종목명을 입력할 수 있는 set_name 메서드를 추가해보세요.
class Stock:
    def __init__(self, name, code):
        self.name = name
        self.code = code

    def set_name(self, name):
        self.name = name


a = Stock(None, None)
a.set_name("삼성전자")  # Stock.set_name(a, "삼성전자")
print(a.name)


# 264. 객체에 종목코드를 입력할 수 있는 set_code 메서드를 추가해보세요.
class Stock:
    def __init__(self, name, code):
        self.name = name
        self.code = code

    def set_name(self, name):
        self.name = name

    def set_code(self, code):
        self.code = code

    def get_name(self):
        return self.name

    def get_code(self):
        return self.code


삼성 = Stock("삼성전자", "005930")
print(삼성.name)
print(삼성.code)
print(삼성.get_name())
print(삼성.get_code())


# 272. 클래스 변수
import random

class Account:
    # class variable
    account_count = 0

    def __init__(self, name, balance):
        self.name = name
        self.balance = balance
        self.bank = "SC은행"

        num1 = random.randint(0, 999)
        num2 = random.randint(0, 99)
        num3 = random.randint(0, 999999)

        num1 = str(num1).zfill(3)      # 1 -> '1' -> '001'
        num2 = str(num2).zfill(2)      # 1 -> '1' -> '01'
        num3 = str(num3).zfill(6)      # 1 -> '1' -> '0000001'
        self.account_number = num1 + '-' + num2 + '-' + num3  # 001-01-000001

        Account.account_count += 1


kim = Account("김민수", 100)
print(Account.account_count)
lee = Account("이민수", 100)
print(Account.account_count)