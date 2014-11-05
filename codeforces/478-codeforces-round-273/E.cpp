#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <vector>

typedef long long Long;

const Long LIMIT = 10000000;

int signum(int x)
{
    return x < 0 ? -1 : x > 0;
}

int check(Long n)
{
    if (n < 10) {
        return 1;
    }
    int direction = signum((n % 10) - (n / 10 % 10));
    if (!direction) {
        return 0;
    }
    for (n /= 10; n >= 10; n /= 10) {
        int new_direction = signum(n % 10 - n / 10 % 10);
        if (direction * new_direction >= 0) {
            return 0;
        }
        direction = new_direction;
    }
    return signum(n % 10 - n / 10 % 10);
}

std::vector <int> lower;

void generate(int length, int number)
{
    if (length < 7) {
        for (int i = 0; i < 10; ++ i) {
            if (number == -1) {
                generate(length + 1, i);
            } else if (number % 10 != i && (number < 10 || signum(number / 10 % 10 - number % 10) != signum(number % 10 - i))) {
                generate(length + 1, number * 10 + i);
            }
        }
    } else {
        lower.push_back(number);
    }
}

int mapping[LIMIT], count[807510][10][2];

int main()
{
    Long m, k;
    std::cin >> m >> k;
    if (m < LIMIT) {
        std::vector <int> higher;
        for (Long i = 1; i < LIMIT ; ++ i) {
            if (check(i)) {
                higher.push_back(i);
                if (i % m == 0 && !(-- k)) {
                    std::cout << i << std::endl;
                    return 0;
                }
            }
        }
        memset(mapping, -1, sizeof(mapping));
        Long base = LIMIT % m;
        generate(0, -1);
        for (int l : lower) {
            mapping[l % m] = 1;
        }
        int n = 0;
        for (int i = 0; i < m; ++ i) {
            if (mapping[i] != -1) {
                mapping[i] = n ++;
            }
        }
        memset(count, 0, sizeof(count));
        for (int l : lower) {
            count[mapping[l % m]][l / (LIMIT / 10)][std::max(signum(l % 10 - l / 10 % 10), 0)] ++;
        }
        for (int h : higher) {
            int r = mapping[(m - base * h % m) % m];
            if (r != -1) {
                for (int d = 0; d < 10; ++ d) {
                    int direction = signum(d - h % 10);
                    if (direction != 0 && (h < 10 || signum(h % 10 - h / 10 % 10) != direction)) {
                        int c = count[r][d][std::max(direction, 0)];
                        if (c >= k) {
                            for (int l : lower) {
                                Long x = h * LIMIT + l;
                                if (l / (LIMIT / 10) == d && x % m == 0 && check(x) && !(-- k)) {
                                    std::cout << x << std::endl;
                                    return 0;
                                }
                            }
                        } else {
                            k -= c;
                        }
                    }
                }
            }
        }
    } else {
        for (Long i = m; i <= LIMIT * LIMIT; i += m) {
            if (check(i) && !(-- k)) {
                std::cout << i << std::endl;
                return 0;
            }
        }
    }
    std::cout << -1 << std::endl;
    return 0;
}
