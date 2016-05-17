#include <cassert>
#include <cstdio>
#include <set>
#include <vector>

typedef long long Long;

const int MOD = 987654321;

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

std::vector<int> multiply(const std::vector<int>& a, const std::vector<int>& b)
{
    int m = a.size();
    std::vector<int> c(m);
    for (int i = 0; i < m; ++ i) {
        if (a[i]) {
            for (int j = 0; j < m; ++ j) {
                if (b[j]) {
                    update(c.at(i ^ j), (Long)a[i] * b[j] % MOD);
                }
            }
        }
    }
    return c;
}

std::vector<int> pow(std::vector<int> a, Long n)
{
    int m = a.size();
    std::vector<int> result(m);
    result[0] = 1;
    while (n) {
        if (n & 1) {
            result = multiply(result, a);
        }
        a = multiply(a, a);
        n >>= 1;
    }
    return result;
}

int f(int n, Long k)
{
    std::vector<int> sg(n + 1);
    std::vector<std::set<int>> successor(n + 1);
    for (int d = 2; d <= n; ++ d) {
        std::set<int> s;
        for (auto&& x : successor[d]) {
            for (auto&& y : successor[d]) {
                s.insert(x ^ y);
            }
        }
        while (s.count(sg[d])) {
            sg[d] ++;
        }
        for (int i = d + d; i <= n; i += d) {
            successor[i].insert(sg[d]);
        }
    }
    int m = *std::max_element(sg.begin(), sg.end()) << 1;
    std::vector<int> count(m);
    for (int d = 2; d <= n; ++ d) {
        count[sg[d]] ++;
    }
    count = pow(count, k);
    int result = 0;
    for (int i = 1; i < m; ++ i) {
        update(result, count[i]);
    }
    return result;
}

int main()
{
    printf("%d\n", f(10, 5));
    printf("%d\n", f(10000000, (Long)1e12));
}
