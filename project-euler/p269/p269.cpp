// Project Euler 269 -- Polynomials with at least one integer root
#include <algorithm>
#include <cstdio>
#include <iostream>

typedef long long Long;

const int OFFSET = 40;

Long ways[17][OFFSET << 1][OFFSET << 1][OFFSET << 1];

Long solve(int n)
{
    Long result = 1;
    for (int i = 0; i < n - 1; ++ i) {
        result *= 10;
    }
    for (int x = 1; x < 10; ++ x) {
        for (int y = x; y < 10; ++ y) {
            for (int z = y; z < 10; ++ z) {
                if (x < y && y == z) {
                    continue;
                }
                memset(ways, 0, sizeof(ways));
                ways[0][OFFSET + 0][OFFSET + 0][OFFSET + 0] = 1;
                for (int i = 0; i < n; ++ i) {
                    for (int a = -OFFSET; a < OFFSET; ++ a) {
                        for (int b = -OFFSET; b < OFFSET; ++ b) {
                            for (int c = -OFFSET; c < OFFSET; ++ c) {
                                Long way = ways[i][OFFSET + a][OFFSET + b][OFFSET + c];
                                if (way) {
                                    for (int d = 0; d < 10; ++ d) {
                                        if (i == n - 1 && d == 0) {
                                            continue;
                                        }
                                        int aa = a * -x + d;
                                        int bb = b * -y + d;
                                        int cc = c * -z + d;
                                        if (-OFFSET <= aa && aa < OFFSET && -OFFSET <= bb && bb < OFFSET && -OFFSET <= cc && cc < OFFSET) {
                                            ways[i + 1][OFFSET + aa][OFFSET + bb][OFFSET + cc] += way;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Long way = ways[n][OFFSET + 0][OFFSET + 0][OFFSET + 0];
                int sign = 1;
                if (x < y) {
                    sign *= -1;
                }
                if (y < z) {
                    sign *= -1;
                }
                result += sign * way;
            }
        }
    }
    return result;
}

int main()
{
    //std::cout << solve(6) << std::endl;
    std::cout << solve(16) << std::endl;
    return 0;
}
