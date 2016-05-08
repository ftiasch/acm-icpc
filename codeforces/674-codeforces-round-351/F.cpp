#include <algorithm>
#include <cstdio>
#include <cstring>
#include <functional>
#include <utility>
#include <vector>

const int P = 130;

unsigned binom[P + 1][P + 1], to_next[P + 1][P + 1], to_result[P + 1][P], ways[P + 1], new_ways[P + 1];

int main()
{
    int n, p, q;
    scanf("%d%d%d", &n, &p, &q);
    memset(binom, 0, sizeof(binom));
    for (int i = 0; i <= p && i <= n; ++ i) {
        binom[i][0] = 1;
        std::vector<unsigned> multiples;
        for (int j = 1; j <= n - i && j <= p; ++ j) {
            multiples.push_back(n - i - j + 1);
            unsigned division = j;
            while (division > 1) {
                for(auto& multiple : multiples) {
                    auto gcd = std::__gcd(division, multiple);
                    division /= gcd;
                    multiple /= gcd;
                }
            }
            unsigned product = 1;
            for(auto&& multiple : multiples) {
                product *= multiple;
            }
            binom[i][j] = product;
        }
    }
    for (int k = 0; k <= p; ++ k) {
        memset(ways, 0, sizeof(ways));
        ways[k] = 1;
        for (int l = 0; l < p; ++ l) {
            memset(new_ways, 0, sizeof(new_ways));
            for (int i = 0; i <= p; ++ i) {
                if (i < n) {
                    to_result[k][l] += ways[i];
                }
                for (int j = i; j <= p; ++ j) {
                    new_ways[j] += ways[i] * binom[i][j - i];
                }
            }
            memcpy(ways, new_ways, sizeof(ways));
        }
        memcpy(to_next[k], ways, sizeof(ways));
    }
    unsigned result = 0;
    memset(ways, 0, sizeof(ways));
    ways[0] = 1;
    for (int i = 0; i <= q; i += p) {
        for (int k = 0; k < p; ++ k) {
            unsigned tmp = 0;
            for (int j = 0; j <= p; ++ j) {
                tmp += ways[j] * to_result[j][k];
            }
            if (i + k <= q) {
                result ^= tmp * (i + k);
            }
        }
        memset(new_ways, 0, sizeof(new_ways));
        for (int j = 0; j <= p; ++ j) {
            for (int k = j; k <= p; ++ k) {
                new_ways[k] += ways[j] * to_next[j][k];
            }
        }
        memcpy(ways, new_ways, sizeof(ways));
    }
    printf("%u\n", result);
}
