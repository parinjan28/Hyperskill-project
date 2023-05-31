
matrix = [[], [], []]
for i in range(3):
    for j in range(3):
        matrix[i].append(" ")
print("---------")
for row in matrix:
    print(f"| {row[0]} {row[1]} {row[2]} |")
print("---------")
x_turn = True
while True:
    while True:
        user_input = input().split()
        if not user_input[0].isnumeric() and not user_input[1].isnumeric():
            print("You should enter numbers!")
            continue
        row_coord = int(user_input[0]) - 1
        column_coord = int(user_input[1]) - 1
        if row_coord not in [0, 1, 2] or column_coord not in [0, 1, 2]:
            print("Coordinates should be from 1 to 3!")
            continue
        elif matrix[row_coord][column_coord] in ["X", "O"]:
            print("This cell is occupied! Choose another one!")
            continue
        break
    matrix[row_coord][column_coord] = "X" if x_turn else "O"
    print("---------")
    for row in matrix:
        print(f"| {row[0]} {row[1]} {row[2]} |")
    print("---------")
    diagonal_left_right = (matrix[0][0] == matrix[1][1] == matrix[2][2]) and (matrix[0][0] in ["X", "O"])
    diagonal_right_left = (matrix[0][2] == matrix[1][1] == matrix[2][0]) and (matrix[0][2] in ["X", "O"])
    both_diagonal = True if diagonal_right_left is True and diagonal_left_right is True else False
    horizontal_check = False
    count_horizontal = 0
    for row in matrix:
        if row[0] == row[1] == row[2] and row[0] in ["X", "O"]:
            horizontal_check = True
            count_horizontal += 1
            winner = row[0]
    vertical_check = False
    count_vertical = 0
    for i in range(3):
        if matrix[0][i] == matrix[1][i] == matrix[2][i] and matrix[0][i] in ["X", "O"]:
            vertical_check = True
            count_vertical += 1
            winner = matrix[0][i]
    game_finished = True if " " not in matrix[0] and " " not in matrix[1] and " " not in matrix[2] else False
    if not horizontal_check and not vertical_check and not diagonal_left_right and not diagonal_right_left and game_finished:
        print("Draw")
        break
    elif horizontal_check:
        print(f"{winner} wins")
        break
    elif vertical_check:
        print(f"{winner} wins")
        break
    elif diagonal_left_right:
        print(f"{matrix[0][0]} wins")
        break
    elif diagonal_right_left:
        print(f"{matrix[2][0]} wins")
        break
    x_turn = False if x_turn is True else True
