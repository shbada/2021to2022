list = [1, 3, 1, 5]

odd_sum = 0
even_sum = 0

for i, value in enumerate(list):
    if i % 2 == 0:
        even_sum += value
    else:
        odd_sum += value


print(max(even_sum, odd_sum))
