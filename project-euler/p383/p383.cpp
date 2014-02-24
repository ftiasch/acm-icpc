// Project Euler 383 -- Divisibility comparison between factorials
#include <iostream>
#include <vector>

typedef long long Long;

const int N = 27;

Long memory[N][N][N][N];

Long go(const std::vector <int> &digits, int n, bool less, int count, int four, int zero)
{
    if (count < 0) {
        return 0;
    }
    if (n == 0) {
        return less && count == 0;
    }
    if (less && ~memory[n][count][four][zero]) {
        return memory[n][count][four][zero];
    }
    Long result = 0;
    for (int d = 0; d < 5; ++ d) {
        if (!less && d > digits[n - 1]) {
            continue;
        }
        if (n <= zero && d > 0) {
            continue;
        }
        if (n == zero + 1 && d == 0) {
            continue;
        }
        result += go(digits, n - 1, less || d < digits[n - 1], count - (d <= 2 ? 0 : 1 + four), (d == 2 ? 1 + four : 0), zero);
    }
    if (less) {
        memory[n][count][four][zero] = result;
    }
    return result;
}

Long solve(Long n)
{
    std::vector <int> digits;
    for (Long x = n + 1; x; x /= 5) {
        digits.push_back(x % 5);
    }
    int m = digits.size();
    Long result = 0;
    for (int count = 0; count <= m; ++ count) {
        for (int zero = count + 1; zero < m; ++ zero) {
            result += go(digits, m, false, count, 0, zero);
        }
    }
    return result;
}

int main()
{
    memset(memory, -1, sizeof(memory));
    std::cout << solve(1000) << std::endl;
    std::cout << solve(1000000000) << std::endl;
    std::cout << solve(1000000000000000000) << std::endl;
}
