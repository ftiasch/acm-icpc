#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

int length, digit[22];
long long memory[2][22][22];

long long solve(bool is_first, bool is_less, int length, int zero_count) {
    if (length == 0) {
        return is_less? zero_count: 0;
    } else {
        if (is_less && memory[is_first][length][zero_count] != -1) {
            return memory[is_first][length][zero_count];
        }
        long long result = 0;
        for (int i = 0; i < 10; ++ i) {
            if (i == 0 && is_first && length > 1) {
                continue;
            }
            if (i > digit[length - 1] && !is_less) {
                continue;
            }
            result += solve(false, is_less || i < digit[length - 1], length - 1, zero_count + (i == 0));
        }
        if (is_less) {
            memory[is_first][length][zero_count] = result;
        }
        return result;
    }
}

long long count_zero(long long n) {
    if (n == 0) {
        return 0;
    }
    length = 0;
    while (n > 0) {
        digit[length ++] = n % 10;
        n /= 10;
    }
    long long result = 0;
    for (int i = length; i >= 1; -- i) {
        result += solve(true, i < length, i, 0);
    }
    return result;
}

int main() {
    ios::sync_with_stdio(false);
    memset(memory, -1, sizeof(memory));
    while (true) {
        long long a, b;
        cin >> a >> b;
        if (a < 0) {
            return false;
        }
        cout << count_zero(b + 1) - count_zero(a) << endl;
    }
    return 0;
}
