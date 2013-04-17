// SGU 289 -- Challenging Tic-Tac-Toe
#include <cstdio>
#include <cstring>
#include <map>
#include <vector>
#include <numeric>
#include <algorithm>

#define ALL(v) (v).begin(), (v).end()

enum Type {LOSE, WIN, DRAW};

std::map <std::vector <int>, Type> memory;

int get_id(int x, int y) {
    return x * 3 + y;
}

const int DELTA[4][2] = {{0, 1}, {1, -1}, {1, 0}, {1, 1}};

// 1 for X, -1 for 0
Type solve(std::vector <int> board) {
    if (memory.find(board) == memory.end()) {
        bool draw = true;
        for (int i = 0; i < 3; ++ i) {
            for (int j = 0; j < 3; ++ j) {
                if (!board[get_id(i, j)]) {
                    draw = false;
                } else {
                    for (int t = 0; t < 4; ++ t) {
                        bool valid = true;
                        for (int k = 0; k < 3; ++ k) {
                            int x = i + k * DELTA[t][0];
                            int y = j + k * DELTA[t][1];
                            valid &= 0 <= x && x < 3 && 0 <= y && y < 3 && board[get_id(i, j)] == board[get_id(x, y)];
                        }
                        if (valid) {
                            draw = false;
                            memory[board] = LOSE;
                        }
                    }
                }
            }
        }
        if (draw) {
            memory[board] = DRAW;
        }
        if (memory.find(board) == memory.end()) {
            Type &ret = memory[board];
            int last = std::accumulate(ALL(board), 0) == 0 ? 1 : -1;
            ret = LOSE;
            for (int i = 0; i < 9; ++ i) {
                if (!board[i]) {
                    std::vector <int> new_board(board);
                    new_board[i] = last;
                    Type tmp = solve(new_board);
                    if (tmp == LOSE) {
                        ret = WIN;
                    }
                    if (tmp == DRAW && ret == LOSE) {
                        ret = DRAW;
                    }
                }
            }
        }
    }
    return memory[board];
}

int main() {
    solve(std::vector <int>(9, 0));
    while (true) {
        char buffer[3][4];
        for (int i = 0; i < 3; ++ i) {
            scanf("%s", buffer[i]);
            if (strcmp(buffer[i], "Qc") == 0) {
                return 0;
            }
        }
        std::vector <int> board(9, 0);
        for (int i = 0; i < 3; ++ i) {
            for (int j = 0; j < 3; ++ j) {
                if (buffer[i][j] == '.') {
                    continue;
                }
                board[get_id(i, j)] = buffer[i][j] == 'X' ? 1 : -1;
            }
        }
        if (memory.find(board) == memory.end()) {
            puts("Illegal position.");
        } else {
            Type ret = memory[board];
            if (ret == DRAW) {
                puts("Game is a draw.");
            } else {
                int winner = std::accumulate(ALL(board), 0) == 0 ? 1 : -1;
                if (ret == LOSE) {
                    winner *= -1;
                }
                printf("%c wins.\n", winner == 1 ? 'X' : '0');
            }
        }
    }
    return 0;
}
