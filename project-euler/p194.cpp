#include <algorithm>
#include <cstdio>
#include <cstring>
#include <vector>

const int MOD = (int)1e8;

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

const int EDGES[][2] = {
    {0, 1},
    {0, 2},
    {0, 3},
    {2, 3},
    {3, 4},
    {4, 5},
    {1, 5},
    {2, 6},
    {5, 6},
    {1, 6}
};

const int N = 7;

int color[N];

auto search(int m, int c, int p)
{
    for (int i = 0; i < m; ++ i) {
        if (EDGES[i][1] < p && color[EDGES[i][0]] == color[EDGES[i][1]]) {
            return 0;
        }
    }
    auto result = 0;
    if (p < N) {
        auto mc = *std::max_element(color, color + p);
        for (color[p] = 0; color[p] <= mc; ++ color[p]) {
            update(result, search(m, c, p + 1));
        }
        color[p] = mc + 1;
        update(result, (c - 1LL - mc) * search(m, c, p + 1) % MOD);
    } else {
        return 1;
    }
    return result;
}

auto solve(int a, int b, int c)
{
    color[0] = 0;
    color[1] = 1;
    auto way_a = search(10, c, 2);
    auto way_b = search(9,  c, 2);
    std::vector<std::vector<int>> ways(a + 1, std::vector<int>(b + 1));
    ways[0][0] = c * (c - 1LL) % MOD;
    for (auto i = 0; i <= a; ++ i) {
        for (auto j = 0; j <= b; ++ j) {
            if (i + 1 <= a) {
                update(ways[i + 1][j], 1LL * ways[i][j] * way_a % MOD);
            }
            if (j + 1 <= b) {
                update(ways[i][j + 1], 1LL * ways[i][j] * way_b % MOD);
            }
        }
    }
    return ways[a][b];
}

int main()
{
    printf("%d\n", solve(1, 0, 3));
    printf("%d\n", solve(0, 2, 4));
    printf("%d\n", solve(2, 2, 3));
    printf("%d\n", solve(25, 75, 1984));
}
