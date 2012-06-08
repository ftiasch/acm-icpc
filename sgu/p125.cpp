// SGU 125 -- Shtirlits
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 3;
const char *NO ="NO SOLUTION";
const int DELTA[4][2] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

int n, a[N][N], b[N][N];

int count_larger(int i, int j) {
    int larger_count = 0;
    for (int k = 0; k < 4; ++ k) {
        int ii = i + DELTA[k][0];
        int jj = j + DELTA[k][1];
        if (0 <= ii && ii < n && 0 <= jj && jj < n) {
            larger_count += a[i][j] < a[ii][jj];
        }
    }
    return larger_count;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            scanf("%d", &b[i][j]);
        }
    }
    if (n == 1) {
        if (b[0][0] == 0) {
            printf("%d\n", 0);
        } else {
            puts(NO);
        }
    } else if (n == 2) {
        bool found = false;
        for (a[0][0] = 0; a[0][0] < 10 && !found; ++ a[0][0]) {
            for (a[0][1] = 0; a[0][1] < 10 && !found; ++ a[0][1]) {
                for (a[1][0] = 0; a[1][0] < 10 && !found; ++ a[1][0]) {
                    for (a[1][1] = 0; a[1][1] < 10 && !found; ++ a[1][1]) {
                        bool check = true;
                        for (int i = 0; i < 2 && check; ++ i) {
                            for (int j = 0; j < 2 && check; ++ j) {
                                check &= count_larger(i, j) == b[i][j];
                            }
                        }
                        if (check) {
                            found = true;
                            printf("%d %d\n%d %d\n", a[0][0], a[0][1], a[1][0], a[1][1]);
                        }
                    }
                }
            }
        }
        if (!found) {
            puts(NO);
        }
    } else {
        bool found = false;
        for (a[1][1] = 0; a[1][1] < 10 && !found; ++ a[1][1]) {
            for (a[0][1] = 0; a[0][1] < 10 && !found; ++ a[0][1]) {
                for (a[1][0] = 0; a[1][0] < 10 && !found; ++ a[1][0]) {
                    for (a[1][2] = 0; a[1][2] < 10 && !found; ++ a[1][2]) {
                        for (a[2][1] = 0; a[2][1] < 10 && !found; ++ a[2][1]) {
                            if (count_larger(1, 1) == b[1][1]) {
                                for (a[0][0] = 0; a[0][0] < 10 && !found; ++ a[0][0]) {
                                    if (count_larger(0, 0) < b[0][0]) {
                                        break;
                                    }
                                    if (count_larger(0, 0) == b[0][0]) {
                                        for (a[0][2] = 0; a[0][2] < 10 && !found; ++ a[0][2]) {
                                            if (count_larger(0, 2) < b[0][2]) {
                                                break;
                                            }
                                            if (count_larger(0, 2) == b[0][2] && count_larger(0, 1) == b[0][1]) {
                                                for (a[2][0] = 0; a[2][0] < 10 && !found; ++ a[2][0]) {
                                                    if (count_larger(2, 0) < b[2][0]) {
                                                        break;
                                                    }
                                                    if (count_larger(2, 0) == b[2][0] && count_larger(1, 0) == b[1][0]) {
                                                        for (a[2][2] = 0; a[2][2] < 10 && !found; ++ a[2][2]) {
                                                            if (count_larger(2, 2) == b[2][2] && count_larger(2, 1) == b[2][1] && count_larger(1, 2) == b[1][2]) {
                                                                found = true;
                                                                printf("%d %d %d\n%d %d %d\n%d %d %d\n", a[0][0], a[0][1], a[0][2], a[1][0], a[1][1], a[1][2], a[2][0], a[2][1], a[2][2]);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!found) {
            puts(NO);
        }
    }
    return 0;
}
