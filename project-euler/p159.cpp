#include <algorithm>
#include <cstdio>
#include <vector>

int digit_root(int n)
{
    auto r = n % 9;
    return r ? r : 9;
}

void update(int& x, int a)
{
    x = std::max(x, a);
}

const int N = 1000000;

int main()
{
    std::vector<int> best(N);
    for (int i = 1; i < N; ++ i) {
        for (int j = 2; i * j < N; ++ j) {
            update(best.at(i * j), best.at(i) + digit_root(j));
        }
    }
    long long result = 0;
    for (int n = 2; n < N; ++ n) {
        result += best.at(n);
    }
    printf("%lld\n", result);
}
