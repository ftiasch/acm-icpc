#include <cstdio>
#include <vector>
#include <utility>
#include <iostream>

const long long INF = 1000000000000000000LL;

int m, h[2], a[2], x[2], y[2];

std::pair <int, int> find(int m, int h, int a, int x, int y) {
    std::vector <int> stamp(m, -1);
    for (int t = 0; t <= m; ++ t) {
        if (~stamp[h]) {
            return {stamp[a], t - stamp[h]};
        }
        stamp[h] = t;
        h = ((long long)h * x + y) % m;
    }
    return {-1, 0};
}

long long solve(int m, std::pair <int, int> r0, std::pair <int, int> r1) {
    long long result = INF;
    for (int i = 0; i < r1.second; ++ i) {
        long long t = r0.first + (long long)r0.second * i;
        if (t >= m && t >= r1.first && (t - r1.first) % r1.second == 0) {
            result = std::min(result, t);
        }
    }
    return result;
}

int main()
{
    scanf("%d", &m);
    std::pair <int, int> r[2];
    for (int i = 0; i < 2; ++ i) {
        scanf("%d%d%d%d", h + i, a + i, x + i, y + i);
        r[i] = find(m, h[i], a[i], x[i], y[i]);
    }
    long long result = INF;
    if (~r[0].first && ~r[1].first) {
        result = std::min(solve(m, r[0], r[1]), solve(m, r[1], r[0]));
    }
    for (int i = 0; i < m; ++ i) {
        if (h[0] == a[0] && h[1] == a[1]) {
            result = std::min(result, (long long)i);
        }
        for (int j = 0; j < 2; ++ j) {
            h[j] = ((long long)h[j] * x[j] + y[j]) % m;
        }
    }
    std::cout << (result < INF ? result : -1) << std::endl;
    return 0;
}
