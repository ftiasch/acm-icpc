#include <cassert>
#include <cstdio>
#include <set>
#include <utility>
#include <vector>

const int N = 50000000;

struct Solution {
    int y = 0, d;

    void update(int yy, int dd) {
        if (y == -1) {
            return;
        }
        if (y == 0) {
            y = yy;
            d = dd;
        } else if (y != yy || d != dd) {
            y = -1;
        }
    }
};

Solution solution[N];

int main()
{
    // for (int d = 1; d * d < N; ++ d) {
    //     for (int n = d * d; n < N; n += d) {
    //         divisors[n].push_back(d);
    //     }
    // }
    for (int a = 1; a * a < N; ++ a) {
        int b = a;
        while (a + b & 3) {
            b ++;
        }
        for (; a * b < N; b += 4) {
            auto d = b + a >> 2;
            auto r = b - a >> 1;
            auto n = a * b;
            solution[n].update(d + d + r, d);
            if (d > r) {
                solution[n].update(d + d - r, d);
            }
        }
    }
    int result = 0;
    for (int n = 1; n < N; ++ n) {
        result += solution[n].y > 0;
    }
    printf("%d\n", result);
}
