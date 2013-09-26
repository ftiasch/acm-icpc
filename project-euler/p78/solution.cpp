#include <cstdio>
#include <vector>

const int MOD = 1000000;

int pentagonal(int n) {
    int k = (n >> 1) + 1;
    if (n & 1) {
        k *= -1;
    }
    return k * (3 * k - 1) / 2;
}

int main() {
    std::vector <int> ways(1, 1);
    while (ways.back() != 0) {
        int n = ways.size();
        ways.push_back(0);
        for (int i = 0; pentagonal(i) <= n; ++ i) {
            int delta = ways[n - pentagonal(i)];
            if (i >> 1 & 1) {
                delta *= -1;
            }
            ways.back() += delta;
        }
        ways.back() %= MOD;
    }
    printf("%d\n", (int)ways.size() - 1);
    return 0;
}
