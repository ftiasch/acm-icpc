// Codeforces Round #156
// Problem E -- Lucky Arrays
#include <cstdio>
#include <cstring>
#include <algorithm>

using std::min;
using std::max;

const int MOD = 777777777;

const int N = 77777;

int n, m;

int w[N - 1][4][4];

int multiply(int a, int b) {
    return (long long)a * b % MOD;
}

void increase(int &x, int a) {
    x += a;
    x %= MOD;
}

const int M = (N << 1) + 1;

int value[N + 1], leftmost[M], rightmost[M], ways[M];

int get_id(int l, int r) {
    return l + r | l != r;
}

void update(int l, int r) {
    int id = get_id(l, r);;
    int m = l + r >> 1;
    leftmost[id] = min(leftmost[get_id(l, m)], leftmost[get_id(m + 1, r)]);
    rightmost[id] = max(rightmost[get_id(l, m)], rightmost[get_id(m + 1, r)]);
    ways[id] = multiply(ways[get_id(l, m)], ways[get_id(m + 1, r)]);
    int i = rightmost[get_id(l, m)];
    int j = leftmost[get_id(m + 1, r)];
    if (i >= 1 && j <= n) {
        ways[id] = multiply(ways[id], w[j - i - 1][value[i]][value[j]]);
    }
}

void update_node(int k) {
    int id = get_id(k, k);
    ways[id] = 1;
    leftmost[id] = value[k] ? k : n + 1;
    rightmost[id] = value[k] ? k : 0;
}

void build(int l, int r) {
    if (l < r) {
        int m = l + r >> 1;
        build(l, m);
        build(m + 1, r);
        update(l, r);
    } else {
        update_node(l);
    }
}

void modify(int l, int r, int k) {
    if (l <= k && k <= r) {
        if (l < r) {
            int m = l + r >> 1;
            modify(l, m, k);
            modify(m + 1, r, k);
            update(l, r);
        } else {
            update_node(k);
        }
    }
}

int main() {
    scanf("%d%d", &n, &m);
    memset(w, 0, sizeof(w));
    for (int i = 1; i <= 3; ++ i) {
        for (int j = 1; j <= 3; ++ j) {
            scanf("%d", &w[0][i][j]);
        }
    }
    for (int s = 0; s + 1 <= n - 2; ++ s) {
        for (int i = 1; i <= 3; ++ i) {
            for (int j = 1; j <= 3; ++ j) {
                for (int k = 1; k <= 3; ++ k) {
                    increase(w[s + 1][i][j], 
                            multiply(w[s][i][k], w[0][k][j]));
                }
            }
        }
    }
//for (int s = 0; s <= n - 2; ++ s) {
//    for (int i = 1; i <= 3; ++ i) {
//        for (int j = 1; j <= 3; ++ j) {
//            printf("%d, ", w[s][i][j]);
//        }
//        puts("");
//    }
//    puts("");
//}
    memset(value, 0, sizeof(value));
    build(1, n);
    while (m --) {
        int k, v;
        scanf("%d%d", &k, &v);
        value[k] = v;
        modify(1, n, k);
        int root = get_id(1, n);
        int i = leftmost[root];
        int j = rightmost[root];
        if (i <= n && j >= 1) {
            int answer = ways[root];
            if (i > 1) {
                int count = 0;
                for (int s = 1; s <= 3; ++ s) {
                    increase(count, w[i - 2][s][value[i]]);
                }
                answer = multiply(answer, count);
            }
            if (j < n) {
                int count = 0;
                for (int t = 1; t <= 3; ++ t) {
                    increase(count, w[n - j - 1][value[j]][t]);
                }
                answer = multiply(answer, count);
            }
            printf("%d\n", answer);
        } else {
            if (n == 1) {
                puts("3");
            } else {
                int answer = 0;
                for (int i = 1; i <= 3; ++ i) {
                    for (int j = 1; j <= 3; ++ j) {
                        increase(answer, w[n - 2][i][j]);
                    }
                }
                printf("%d\n", answer);
            }
        }
    }
    return 0;
}
