my_list = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
left_constructor = []
layer = int(input("Layers: "))
count = -layer + 1
for i in range(layer - 1, -1, -1): #change 4 to layer
    inside = my_list[i] * (i)
    if count + i != 0:
        num = abs(count + i)
        outside = left_constructor[num - 1][:num] + inside
        inside = outside
    left_constructor.append(inside)
right_constructor = []
for j in left_constructor:
    right_constructor.append(j[::-1])
bottom_list = []
for k in range(layer, 0, -1):
    bottom_list.append(left_constructor[abs(k-layer)] + my_list[k - 1] + right_constructor[abs(k-layer)])
    print(left_constructor[abs(k-layer)] + my_list[k - 1] + right_constructor[abs(k-layer)])
bottom_list.reverse()
bottom_list.pop(0)
for l in bottom_list:
    print(l)

