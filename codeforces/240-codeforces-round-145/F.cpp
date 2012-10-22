// Codeforces Round #145
// Problem F -- TopCoder
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100000;

int n, m;
char text[N + 1];

int counter[N << 1][26], marker[N << 1];

inline int index(int l, int r) {
    return l + r | l != r;
}

void update(int l, int r) {
    int m = (l + r) >> 1;
    for (int i = 0; i < 26; ++ i) {
        counter[index(l, r)][i] = counter[index(l, m)][i] + counter[index(m + 1, r)][i];
    }
}

void build(int l, int r) {
    if (l == r) {
        marker[index(l, r)] = text[l] - 'a';
        counter[index(l, r)][text[l] - 'a'] ++;
    } else {
        int m = (l + r) >> 1;
        build(l, m);
        build(m + 1, r);
        update(l, r);
    }
}

inline void mark(int l, int r, int c) {
    int t = index(l, r);
    marker[t] = c;
    memset(counter[t], 0, sizeof(counter[t]));
    counter[t][c] = r - l + 1;
}

inline void push(int l, int r) {
    int t = index(l, r);
    if (marker[t] != -1) {
        int m = (l + r) >> 1;
        mark(l, m, marker[t]);
        mark(m + 1, r, marker[t]);
        marker[t] = -1;
    }
}

void cover(int l, int r, int a, int b, int c) {
    if (b < l || r < a) {
        return;
    }
    if (a <= l && r <= b) {
        mark(l, r, c);
    } else {
        push(l, r);
        int m = (l + r) >> 1;
        cover(l, m, a, b, c);
        cover(m + 1, r, a, b, c);
        update(l, r);
    }
}

int result[26];

void query(int l, int r, int a, int b) {
    if (b < l || r < a) {
        return;
    }
    int t = index(l, r);
    if (marker[t] != -1) {
        result[marker[t]] += min(r, b) - max(l, a) + 1;
        return;
    }
    if (a <= l && r <= b) {
        for (int i = 0; i < 26; ++ i) {
            result[i] += counter[t][i];
        }
        return;
    }
    int m = (l + r) >> 1;
    query(l, m, a, b);
    query(m + 1, r, a, b);
}

void output(int l, int r) {
    if (l < r) {
        push(l, r);
        int m = (l + r) >> 1;
        output(l, m);
        output(m + 1, r);
    } else {
        putchar('a' + marker[index(l, r)]);
    }
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d%d%s", &n, &m, text);
    memset(marker, -1, sizeof(marker));
    memset(counter, 0, sizeof(counter));
    build(0, n - 1);
    while (m --) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        memset(result, 0, sizeof(result));
        query(0, n - 1, a, b);
        int odds = 0;
        for (int i = 0; i < 26; ++ i) {
            odds += result[i] & 1;
        }
        if (odds <= 1 && odds == (b - a + 1 & 1)) {
            for (int i = 0; i < 26; ++ i) {
                if (result[i] > 1) {
                    int l = result[i] >> 1;
                    cover(0, n - 1, a, b, i);
                    a += l;
                    b -= l;
                }
            }
            if (odds) {
                int c = 0;
                while (!(result[c] & 1)) {
                    c ++;
                }
                int i = (a + b) >> 1;
                cover(0, n - 1, i, i, c);
            }
        }
    }
    output(0, n - 1);
    puts("");
    return 0;
}
