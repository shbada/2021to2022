# coding=utf-8

# if 문 사용
# if 조건문: 바로 아래 문장부터 if문에 속하는 모든 문장에 들여쓰기(indentation) 필수
money = True
if money:
    print("택시를 타고 가라")
else:
    print("걸어 가라")

pocket = ['paper', 'cellphone', 'money']
if 'money' in pocket:
    print("택시를 타고 가라")
else:
    print("걸어가라")

# pass
pocket = ['paper', 'money', 'cellphone']
if 'money' in pocket:
    pass # 아무것도 실행하지 않는다.
else:
    print("카드를 꺼내라")

# elif 사용
pocket = ['paper', 'handphone']
card = True

"""
if 'money' in pocket:
    print("택시를 타고가라")
    else:
        if card:
            print("택시를 타고가라")
        else:
            print("걸어가라")
"""

# 위 if-else(if-else)를 아래 elif로 표현
pocket = ['paper', 'cellphone']
card = True
if 'money' in pocket:
    print("택시를 타고가라")
elif card:
    print("택시를 타고가라")
else:
    print("걸어가라")

