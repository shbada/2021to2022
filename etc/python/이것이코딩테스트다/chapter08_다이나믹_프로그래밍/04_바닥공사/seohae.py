# 세로 : 2 고정
# 가로 N

# 덮개 3가지
# 세로 1 가로 2, 세로 2 가로 1, 세로 2 가로 2

N = 3

# 각 인덱스로 매칭
width = [2, 1, 2]
height = [1, 2, 2]

count = 0

for i in range(1, N + 1):
    for j in width:
        if i == width[j] and height[j] == 2:
            count = 1




