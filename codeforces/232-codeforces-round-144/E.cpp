// Codeforces Round #144
// Problem E -- Quick Tortoise
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

struct Bitset {
    unsigned long long mask[8];

    void clear() {
        memset(mask, 0, sizeof(mask));
    }

    void set(int i) {
        mask[i >> 6] |= 1ULL << (i & 63);
    }

    unsigned long long &operator[](int i) {
        return mask[i];
    }

    const unsigned long long &operator[](int i) const {
        return mask[i];
    }

    Bitset &operator |=(const Bitset &o) {
        for (int i = 0; i < 8; ++ i) {
            mask[i] |= o[i];
        }
        return *this;
    }
};

bool intersect(const Bitset &a, const Bitset &b) {
    for (int i = 0; i < 8; ++ i) {
        if (a[i] & b[i]) {
            return true;
        }
    }
    return false;
}

const int N = 500;
const int Q = 600000;

int n, m, q, x[Q][2], y[Q][2];
char map[N][N + 1];

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

Bitset down[N][N], up[N][N];
bool result[Q];

void solve(int l, int r, vector <int> *queries) {
    if (l < r) {
        int d = (l + r) >> 1;
        for (int i = d; i >= l; -- i) {
            for (int j = m - 1; j >= 0; -- j) {
                down[i][j].clear();
                if (map[i][j] == '#') {
                    continue;
                }
                if (i == d) {
                    down[i][j].set(j);
                }
                if (i + 1 <= d) {
                    down[i][j] |= down[i + 1][j];
                }
                if (j + 1 < m) {
                    down[i][j] |= down[i][j + 1];
                }
            }
        }
        for (int i = d; i < r; ++ i) {
            for (int j = 0; j < m; ++ j) {
                up[i][j].clear();
                if (map[i][j] == '#') {
                    continue;
                }
                if (i == d) {
                    up[i][j].set(j);
                }
                if (i - 1 >= d) {
                    up[i][j] |= up[i - 1][j];
                }
                if (j - 1 >= 0) {
                    up[i][j] |= up[i][j - 1];
                }
            }
        }
        vector <int> *leftQueries = new vector <int>();
        vector <int> *rightQueries = new vector <int>();
        foreach (iter, *queries) {
            int id = *iter;
            if (x[id][1] < d) {
                leftQueries->push_back(id);
            } else if (d < x[id][0]) {
                rightQueries->push_back(id);
            } else {
                result[id] = intersect(down[x[id][0]][y[id][0]], up[x[id][1]][y[id][1]]);
            }
        }
        delete queries;
        solve(l, d, leftQueries);
        solve(d + 1, r, rightQueries);
    }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    scanf("%d", &q);
    vector <int> *queries = new vector <int>();
    for (int i = 0; i < q; ++ i) {
        scanf("%d%d%d%d", &x[i][0], &y[i][0], &x[i][1], &y[i][1]);
        x[i][0] --;
        y[i][0] --;
        x[i][1] --;
        y[i][1] --;
        queries->push_back(i);
    }
    solve(0, n, queries);
    for (int i = 0; i < q; ++ i) {
        puts(result[i] ? "Yes" : "No");
    }
    return 0;
}
