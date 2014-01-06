#include <cstdio>
#include <cstring>
#include <functional>
#include <algorithm>

const int N = 100;
const int M = 20;

int n, m, strength[N], team[M];
char action[M];

int maximum[1 << M];

int min(int a, int b) {
    return a < b ? a : b;
}

int max(int a, int b) {
    return a > b ? a : b;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", strength + i);
    }
    scanf("%d", &m);
    for (int i = 0; i < m; ++ i) {
        static char buffer[2];
        scanf("%s%d", buffer, team + i);
        action[i] = *buffer;
        team[i] --;
    }
    std::sort(strength, strength + n, std::greater <int>());
    memset(maximum, 0, sizeof(maximum));
    for (int mask = (1 << m) - 2; mask >= 0; -- mask) {
        int i = __builtin_popcount(mask);
        int sign = team[i] ? 1 : -1;
        std::function <int(int, int)> opt = team[i] ? min : max;
        maximum[mask] = sign * 1000000000;
        for (int j = 0; j < m; ++ j) {
            if (~mask >> j & 1) {
                if (action[i] == 'p') {
                    maximum[mask] = opt(maximum[mask], maximum[mask | 1 << j] - sign * strength[j]);
                } else {
                    maximum[mask] = opt(maximum[mask], maximum[mask | 1 << j]);
                }
            }
        }
    }
    printf("%d\n", maximum[0]);
    return 0;
}
