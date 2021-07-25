A = [1, 2, 5, 4, 3]
B = [5, 5, 6, 6 ,5]

k = 3

A.sort()  # 오름차순 정렬
B.sort(reverse=True)  # 내림차순 정렬

for i in range(k):
    if A[i] < B[i]:
        A[i], B[i] = B[i], A[i]

print(A)
print(sum(A))

