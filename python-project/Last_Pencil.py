import random


def process(pencil : int, name : str):
    while True and name == "John":
        pencil_taken = input()
        if pencil_taken.isnumeric():
            if int(pencil_taken) > pencil:
                print("Possible values: '1', '2', '3'")
                print("Too many pencils were taken")
                continue
            elif int(pencil_taken) not in [1, 2, 3]:
                print("Possible values: '1', '2', '3'")
                continue
        else:
            print("Possible values: '1', '2', '3'")
            continue
        pencil -= int(pencil_taken)
        break
    if name == "Jack":
        losing = (pencil - 1) % 4 == 0
        subtract = random.randint(1, 3)
        if losing and pencil - subtract >= 0:
            print(subtract)
            pencil -= subtract
        elif losing and pencil - subtract < 0:
            print(pencil)
            pencil -= pencil
        else:
            if pencil % 4 == 0:
                print(3)
                pencil -= 3
            elif (pencil + 1) % 4 == 0:
                print(2)
                pencil -= 2
            else:
                print(1)
                pencil -= 1
    return pencil


while True:
    pencil = input("How many pencils would you like to use:")
    if not pencil.isnumeric():
        print("The number of pencils should be numeric")
        continue
    if pencil == "0":
        print("The number of pencils should be positive")
        continue
    pencil = int(pencil)
    break
while True:
    name = input("Who will be the first (John, Jack):")
    if name != "Jack" and name != "John":
        print("Choose between John and Jack")
        continue
    break
print(pencil * "|")
while pencil > 0:
    print(f"{name}'s turn!")
    pencil = process(pencil, name)
    if pencil > 0:
        print(pencil * "|")
    name = "John" if name == "Jack" else "Jack"
print(f"{name} won!")
