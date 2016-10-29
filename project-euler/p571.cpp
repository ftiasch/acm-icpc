#include <algorithm>
#include <cstdio>
#include <iostream>
#include <numeric>
#include <vector>

const int N = 12;

int main()
{
    std::vector<int> digits(N);
    std::iota(digits.begin(), digits.end(), 0);
    std::swap(digits.at(0), digits.at(1));
    int quota = 10;
    long long sum = 0;
    do {
        long long number = 0;
        for (int i = 0; i < N; ++ i) {
            number = number * N + digits.at(i);
        }
        bool valid = true;
        for (int base = 2; base < N && valid; ++ base) {
            std::vector<bool> presented(base);
            long long tmp = number;
            while (tmp) {
                presented[tmp % base] = true;
                tmp /= base;
            }
            valid &= std::find(presented.begin(), presented.end(), false) == presented.end();
        }
        if (valid) {
            quota --;
            sum += number;
            for (int i = 0; i < N; ++ i) {
                fprintf(stderr, "%d%c", digits.at(i), " \n"[i == N - 1]);
            }
        }
    } while (quota && std::next_permutation(digits.begin(), digits.end()));
    std::cout << sum << std::endl;
}
