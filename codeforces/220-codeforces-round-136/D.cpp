// Codeforces Round #136
// Problem D -- Little Elephant and Triangle
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int MOD = 1000000007;

const int N = 4000 + 1;

int n, m;
int group[2][2], visit[2][2];
int xcount[N], ycount[N];
int memory[N][N];

int gcd(int i, int j) {
    if (j == 0) {
        return i;
    }
    if (memory[i][j] == -1) {
        memory[i][j] = gcd(j, i % j);
    }
    return memory[i][j];
}

int main() {
    scanf("%d%d", &n, &m);
    memset(group, 0, sizeof(group));
    for (int i = 0; i <= n; ++ i) {
        for (int j = 0; j <= m; ++ j) {
            group[i & 1][j & 1] ++;
        }
    }
    memset(visit, 0, sizeof(visit));
    int result = 0;
    for (int x_0 = 0; x_0 < 2; ++ x_0) {
        for (int y_0 = 0; y_0 < 2; ++ y_0) {
            int counter_0 = group[x_0][y_0];
            visit[x_0][y_0] ++;
            for (int x_1 = 0; x_1 < 2; ++ x_1) {
                for (int y_1 = 0; y_1 < 2; ++ y_1) {
                    int counter_1 = (long long)counter_0 * (group[x_1][y_1] - visit[x_1][y_1]) % MOD;
                    visit[x_1][y_1] ++;
                    for (int x_2 = 0; x_2 < 2; ++ x_2) {
                        for (int y_2 = 0; y_2 < 2; ++ y_2) {
                            int counter_2 = (long long)counter_1 * (group[x_2][y_2] - visit[x_2][y_2]) % MOD;
                            if (((x_1 - x_0) * (y_2 - y_0) - (y_1 - y_0) * (x_2 - x_0)) % 2 == 0) {
                                result = (result + counter_2) % MOD;
                            }
                        }
                    }
                    visit[x_1][y_1] --;
                }
            }
            visit[x_0][y_0] --;
        }
    }
    memset(xcount, 0, sizeof(xcount));
    memset(ycount, 0, sizeof(ycount));
    for (int i = 0; i <= n; ++ i) {
        for (int j = 0; j <= n; ++ j) {
            xcount[abs(i - j)] ++;
        }
    }
    for (int i = 0; i <= m; ++ i) {
        for (int j = 0; j <= m; ++ j) {
            ycount[abs(i - j)] ++;
        }
    }
    memset(memory, -1, sizeof(memory));
    int counter = 0;
    for (int i = 0; i <= n; ++ i) {
        for (int j = 0; j <= m; ++ j) {
            if (i != 0 || j != 0) {
                counter = (counter + ((long long)gcd(i, j) - 1) * xcount[i] % MOD * ycount[j] % MOD) % MOD;
            }
        }
    }
    counter = (3LL * counter) % MOD;
    result = (result + MOD - counter) % MOD;
    printf("%d\n", result);
    return 0;
}
