# coding=utf-8
# 만약 4가 1, 2, 3, 4 중에 있으면 "4가 있습니다"를 출력한다.
if 4 in [1,2,3,4]: print("4가 있습니다")

# simple.py
languages = ['python', 'perl', 'c', 'java']

for lang in languages:
    if lang in ['python', 'perl']:
        print("%6s need interpreter" % lang)
    elif lang in ['c', 'java']:
        print("%6s need compiler" % lang)
    else:
        print("should not reach here")