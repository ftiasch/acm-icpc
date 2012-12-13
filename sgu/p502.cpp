// SGU 502 -- Digits Permutation
#include <cstdio>
#include <cstring>
#include <string>
#include <utility>
#include <iostream>

std::string s;
std::pair <int, int> approach[1 << 18][17];

void construct(int mask, int r) {
    if (mask) {
        int i = approach[mask][r].second;
        construct(mask ^ (1 << i), approach[mask][r].first);
        putchar(s[i]);
    }
}

int main() {
    std::cin >> s;
    int n = s.size();
    for (int mask = 0; mask < 1 << n; ++ mask) {
        for (int r = 0; r < 17; ++ r) {
            approach[mask][r] = std::make_pair(-1, -1);
        }
    }
    approach[0][0] = std::make_pair(0, 0);
    for (int mask = 0; mask < 1 << n; ++ mask) {
        for (int r = 0; r < 17; ++ r) {
            if (approach[mask][r].first != -1) {
                for (int i = 0; i < n; ++ i) {
                    if ((~mask >> i & 1) && (mask || s[i] != '0')) {
                        int nr = (r * 10 + s[i] - '0') % 17;
                        approach[mask | 1 << i][nr] = std::make_pair(r, i);
                    }
                }
            }
        }
    }
    if (approach[(1 << n) - 1][0].first != -1) {
        construct((1 << n) - 1, 0);
        puts("");
    } else {
        puts("-1");
    }
    return 0;
}
