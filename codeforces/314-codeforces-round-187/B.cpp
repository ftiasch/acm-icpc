// Codeforces Round #187
// Problem B -- Sereja and Periods
#include <cstdio>
#include <cstring>
#include <utility>

const int N = 100;
const int D = 30;

const int INF = (int)1e9 + 1;

typedef std::pair <int, long long> Pair;

int p, q, n, m;
char a[N + 1], b[N + 1];

Pair go[D][N * N];

int get_id(int i, int j) {
    return i * m + j;
}

long long solve(int times) {
    int now = get_id(0, 0);
    long long count = 0;
    for (int i = 0; i < D; ++ i) {
        if (times >> i & 1) {
            count += go[i][now].second;
            now = go[i][now].first;
        }
    }
    return count;
}

int main() {
    scanf("%d%d%s%s", &p, &q, a, b);
    n = strlen(a);
    m = strlen(b);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            int id = get_id(i, j);
            go[0][id] = Pair(id, INF);
            for (int k = 0; k < n; ++ k) {
                if (a[(i + k) % n] == b[j]) {
                    go[0][id] = Pair(get_id((i + k + 1) % n, (j + 1) % m), i == 0 || i + k >= n);
                    break;
                }
            }
        }
    }
    for (int i = 0; i + 1 < D; ++ i) {
        for (int j = 0; j < n * m; ++ j) {
            int next = go[i][j].first;
            go[i + 1][j] = Pair(go[i][next].first, go[i][j].second + go[i][next].second);
        }
    }
    int low = 0;
    int high = 1000000000;
    while (low < high) {
        int middle = low + high + 1 >> 1;
        if (solve(middle) <= p) {
            low = middle;
        } else {
            high = middle - 1;
        }
    }
    printf("%d\n", low / (m * q));
    return 0;
}
