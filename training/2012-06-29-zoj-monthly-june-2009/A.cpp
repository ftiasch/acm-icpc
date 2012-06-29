// Problem A -- Beautiful Meadow
#include <cassert>
#include <cstdio>
#include <cstring>
#include <string>
#include <algorithm>
using namespace std;

const int N = 10;
const int M = 500000;
const int MOD = 99991;
const string CHAR = "*1()";

int n, m, weight[N][N], pointer, stateCount[2], values[2][M], nextState[M], hash[MOD], states[2][M];

inline int get(int state, int k) {
    return state >> (k << 1) & 3;
}

inline int set(int state, int k, int v) {
    return state ^ ((get(state, k) ^ v) << (k << 1));
}

void print(int state) {
    for (int i = 0; i <= m; ++ i) {
        printf("%c", CHAR[state >> (i << 1) & 3]);
    }
    puts("");
}

void update(int state, int value) {
    int h = state % MOD;
    for (int iter = hash[h]; iter != -1; iter = nextState[iter]) {
        if (states[pointer][iter] == state) {
            values[pointer][iter] = max(values[pointer][iter], value);
            return;
        }
    }
    int iter = stateCount[pointer] ++;
    states[pointer][iter] = state;
    values[pointer][iter] = value;
    nextState[iter] = hash[h];
    hash[h] = iter;
}

int findMatch(int state, int start, int init) {
    int delta = (init & 1) == 0? 1: -1;
    int count = delta;
    for (int i = start + delta; ; i += delta) {
        if ((get(state, i) >> 1) == 1) {
            if ((get(state, i) & 1) == 0) {
                count ++;
            } else {
                count --;
            }
        }
        if (count == 0) {
            return i;
        }
    }
    return -1;
}

int main() {
    int testCount;
    scanf("%d", &testCount);
    while (testCount --) {
        scanf("%d%d", &n, &m);
        memset(weight, 0, sizeof(weight));
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                scanf("%d", &weight[i][j]);
            }
        }
        pointer = 0;
        stateCount[pointer] = 0;
        memset(hash, -1, sizeof(hash));
        update(0, 0);
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                result = max(result, weight[i][j]);
                pointer ^= 1;
                stateCount[pointer] = 0;
                memset(hash, -1, sizeof(hash));
                for (int k = 0; k < stateCount[pointer ^ 1]; ++ k) {
                    int state = states[pointer ^ 1][k];
                    int value = values[pointer ^ 1][k];
                    if (j == 0) {
                        state = state << 2;
                    }
                    if (weight[i][j] == 0) {
                        if (get(state, j) == 0 && get(state, j + 1) == 0) {
                            update(state, value);
                        }
                    } else {
                        int left = get(state, j);
                        int up = get(state, j + 1);
                        state = set(set(state, j, 0), j + 1, 0);
                        if (left == 0 && up == 0) {
                            update(state, value);
                            if (weight[i + 1][j] > 0) {
                                update(set(state, j, 1), value + weight[i][j]);
                            }
                            if (weight[i][j + 1] > 0) {
                                update(set(state, j + 1, 1), value + weight[i][j]);
                            }
                            if (weight[i + 1][j] > 0 && weight[i][j + 1] > 0) {
                                update(set(set(state, j, 2), j + 1, 3), value + weight[i][j]);
                            }
                        } else if (left == 0 || up == 0) {
                            int all = left | up;
                            if (all == 1) {
                                result = max(result, value + weight[i][j]);
                            } else {
                                int k = left != 0? findMatch(state, j, left): findMatch(state, j + 1, up);
                                update(set(state, k, 1), value + weight[i][j]);
                            }
                            if (weight[i + 1][j] > 0) {
                                update(set(state, j, all), value + weight[i][j]);
                            }
                            if (weight[i][j + 1] > 0) {
                                update(set(state, j + 1, all), value + weight[i][j]);
                            }
                        } else {
                            if (left == 1 && up == 1) {
                                result = max(result, value + weight[i][j]);
                            } else if (left == 1 || up == 1) {
                                int k = left != 1? findMatch(state, j, left): findMatch(state, j + 1, up);
                                update(set(state, k, 1), value + weight[i][j]);
                            } else {
                                if (left == 2) {
                                    if (up == 2) {
                                        update(set(state, findMatch(state, j + 1, up), 2), value + weight[i][j]);
                                    }
                                } else {
                                    if (up == 2) {
                                        update(state, value + weight[i][j]);
                                    } else {
                                        update(set(state, findMatch(state, j, left), 3), value + weight[i][j]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        printf("%d\n", result);
    }
    return 0;
}
