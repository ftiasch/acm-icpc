#include <bitset>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <vector>

int f(int n)
{
    int result = 0;
    for (int a = 1; a < 1 << n; ++ a) {
        for (int b = 1; b < a; ++ b) {
            if ((a & b) || __builtin_popcount(a) != __builtin_popcount(b)) {
                continue;
            }
            bool positive = false;
            bool negative = false;
            int delta = 0;
            for (int i = n - 1; i >= 0; -- i) {
                delta += a >> i & 1;
                delta -= b >> i & 1;
                positive |= delta > 0;
                negative |= delta < 0;
            }
            result += positive && negative;
            // if (positive && negative) {
            //     std::cout << std::bitset<4>(a) << "\n" << std::bitset<4>(b) << "\n";
            //     int delta = 0;
            //     for (int i = n - 1; i >= 0; -- i) {
            //         delta += a >> i & 1;
            //         delta -= b >> i & 1;
            //         positive |= delta > 0;
            //         negative |= delta < 0;
            //         printf("%d,", delta);
            //     }
            //     puts("");
            // }
        }
    }
    return result;
}

int main()
{
    for (auto&& n : std::vector<int> {4, 7, 12}) {
        printf("f(%d) = %d\n", n, f(n));
    }
}
