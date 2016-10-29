#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <vector>

const int N = 120000;

int rad[N];
long long result;

void generate(int p1, int q1, int p2, int q2)
{
    int a = p1 + p2;
    int c = q1 + q2;
    if (c < N) {
        generate(p1, q1, a, c);
        int b = c - a;
        if ((long long)rad[a] * rad[b] * rad[c] < c) {
            result += c;
        }
        generate(a, c, p2, q2);
    }
}

int main()
{
    std::vector<int> primes;
    memset(rad, 0, sizeof(rad));
    rad[1] = 1;
    for (int d = 2; d < N; ++ d) {
        if (!rad[d]) {
            primes.push_back(d);
            rad[d] = d;
        }
        for (auto&& p : primes) {
            if (d * p >= N) {
                break;
            }
            if (d % p == 0) {
                rad[d * p] = rad[d];
                break;
            } else {
                rad[d * p] = rad[d] * p;
            }
        }
    }
    generate(0, 1, 1, 2);
    printf("%lld\n", result);
}
