// SGU 372 -- Tea Party
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>

const int N = 1000;
const long long INF = 1000000000000000000LL;

int n, m, price[N], kind[N];
long long cost[N + 1][N + 1][4];
std::vector <int> order[2];

bool by_price(int i, int j) {
    return price[i] < price[j];
}

void update(long long &x, long long a) {
    x = std::min(x, a);
}

void construct(int i, int j, int mask) {
    if (i == 0 && j == 0) {
        return;
    }
    int now = m - (i + j - 1);
    for (int next = 0; next < 4; ++ next) {
        if (i && next != 0 && ((next << 1 | 0) & 3) == mask && cost[i - 1][j][next] + now * price[order[0][i - 1]] == cost[i][j][mask]) {
            construct(i - 1, j, next);
            printf("%d ", order[0][i - 1] + 1);
            return;
        }
        if (j && next != 3 && ((next << 1 | 1) & 3) == mask && cost[i][j - 1][next] + now * price[order[1][j - 1]] == cost[i][j][mask]) {
            construct(i, j - 1, next);
            printf("%d ", order[1][j - 1] + 1);
            return;
        }
    }
}

int main() {
    scanf("%d%d", &m, &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", price + i, kind + i);
        order[kind[i]].push_back(i);
    }
    for (int i = 0; i < 2; ++ i) {
        std::sort(order[i].begin(), order[i].end(), by_price);
    }
    for (int i = 0; i <= (int)order[0].size(); ++ i) {
        for (int j = 0; j <= (int)order[1].size(); ++ j) {
            for (int mask = 0; mask < 4; ++ mask) {
                cost[i][j][mask] = INF;
            }
        }
    }
    cost[0][0][1] = cost[0][0][2] = 0;
    for (int i = 0; i <= (int)order[0].size(); ++ i) {
        for (int j = 0; j <= (int)order[1].size(); ++ j) {
            for (int mask = 0; mask < 4; ++ mask) {
                if (cost[i][j][mask] == INF || i + j >= m) {
                    continue;
                }
                int now = m - (i + j);
                if (i < (int)order[0].size() && mask != 0) {
                    update(cost[i + 1][j][(mask << 1 | 0) & 3], cost[i][j][mask] + now * price[order[0][i]]);
                }
                if (j < (int)order[1].size() && mask != 3) {
                    update(cost[i][j + 1][(mask << 1 | 1) & 3], cost[i][j][mask] + now * price[order[1][j]]);
                }
            }
        }
    }
    long long answer = INF;
    for (int i = 0; i <= (int)order[0].size(); ++ i) {
        int j = m - i;
        if (0 <= j && j <= (int)order[1].size()) {
            for (int mask = 0; mask < 4; ++ mask) {
                answer = std::min(answer, cost[i][j][mask]);
            }
        }
    }
    if (answer == INF) {
        puts("Impossible");
        return 0;
    } 
    for (int i = 0; i <= (int)order[0].size(); ++ i) {
        int j = m - i;
        if (0 <= j && j <= (int)order[1].size()) {
            for (int mask = 0; mask < 4; ++ mask) {
                if (cost[i][j][mask] == answer) {
                    construct(i, j, mask);
                    puts("");
                    return 0;
                }
            }
        }
    }
    return 0;
}
