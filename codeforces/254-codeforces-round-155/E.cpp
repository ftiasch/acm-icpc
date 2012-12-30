// Codeforces Round #155
// Problem E -- Dormitory    
#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::make_pair;

const int N = 400;

int n, v, m, a[N], f[N];
std::vector <int> friends[N], arrangement[N];

bool by_f(int i, int j) {
    return f[i] < f[j];
}

typedef std::pair <int, std::pair <int, int> > Pair;

Pair maximum[N + 1][N + 1];

void update(Pair &x, const Pair &a) {
    x = std::max(x, a);
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d%d", &n, &v);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    scanf("%d", &m);
    for (int i = 0; i < m; ++ i) {
        int l, r;
        scanf("%d%d%d", &l, &r, f + i);
        for (int k = l - 1; k < r; ++ k) {
            friends[k].push_back(i);
        }
    }
    for (int i = 0; i < n; ++ i) {
        std::sort(friends[i].begin(), friends[i].end(), by_f);
    }
    for (int i = 0; i <= n; ++ i) {
        for (int j = 0; j <= N; ++ j) {
            maximum[i][j] = make_pair(INT_MIN, make_pair(-1, -1));
        }
    }
    maximum[0][0] = make_pair(0, make_pair(-1, -1));
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j <= N; ++ j) {
            if (maximum[i][j].first != INT_MIN) {
                int left = a[i] + j - v;
                for (int k = 0; k <= (int)friends[i].size() && left >= 0; ++ k) {
                    if (k) {
                        left -= f[friends[i][k - 1]];
                    }
                    if (left >= 0) {
                        update(maximum[i + 1][std::min(a[i], left)], make_pair(maximum[i][j].first + k, make_pair(j, k)));
                    }
                }
            }
        }
    }
    int j = std::max_element(maximum[n], maximum[n] + (N + 1)) - maximum[n];
    printf("%d\n", maximum[n][j].first);
    for (int i = n - 1; i >= 0; -- i) {
        std::pair <int, int> candidate = maximum[i + 1][j].second;
        int previous_j = candidate.first;
        int amount = candidate.second;
        for (int k = 0; k < amount; ++ k) {
            arrangement[i].push_back(friends[i][k]);
        }
        j = previous_j;
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d", (int)arrangement[i].size());
        foreach (iter, arrangement[i]) {
            printf(" %d", 1 + *iter);
        }
        puts("");
    }
    return 0;
}
