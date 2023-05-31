# Write your solution here
def row_correct(matrix: list, row: int) -> bool:
    for i in range(1, 10):
        if matrix[row].count(i) > 1:
            return False
    return True
def column_correct(matrix: list, column: int) -> bool:
    new_list = []
    for i in matrix:
        new_list.append(i[column])
    for j in range(1, 10):
        if new_list.count(j) > 1:
            return False
    return True
def check_grid(matrix: list, row: int, column: int) -> bool:
    new_list = []
    for i in range(row, row + 3):
        for j in range(column, column + 3):
            new_list.append(matrix[i][j])
    for k in range(1, 10):
        if new_list.count(k) > 1:
            return False
    return True
def sudoku_grid_correct(sudoku : list):
    for i in range(0, 9):
        if not row_correct(sudoku, i) or not column_correct(sudoku, i):
            return False
    for j in range(0, len(sudoku) - 2, 3):
        for k in range(0, len(sudoku) - 2, 3):
            if not check_grid(sudoku, j, k):
                return False
    return True

if __name__ == "__main__":

    sudoku2 = [
    [2, 6, 7, 8, 3, 9, 5, 0, 4],
    [9, 0, 3, 5, 1, 0, 6, 0, 0],
    [0, 5, 1, 6, 0, 0, 8, 3, 9],
    [5, 1, 9, 0, 4, 6, 3, 2, 8],
    [8, 0, 2, 1, 0, 5, 7, 0, 6],
    [6, 7, 4, 3, 2, 0, 0, 0, 5],
    [0, 0, 0, 4, 5, 7, 2, 6, 3],
    [3, 2, 0, 0, 8, 0, 0, 5, 7],
    [7, 4, 5, 0, 0, 3, 9, 0, 1]
    ]
    print(sudoku_grid_correct(sudoku2))
