#include <algorithm>
#include <cmath>
#include <iostream>
#include <vector>

typedef long long Long;

Long n;

int main() {
    std::cin >> n;
    std::vector <Long> result;
    for (int p = 0; (1LL << p) - 1 <= n; ++ p) {
        Long low = 1;
        Long high = (Long)sqrt(n * 2) + 1;
        if (p) {
            high = std::min(high, n / ((1LL << p) - 1));
        }
        while (low < high) {
            Long middle = low + high + 1 >> 1;
            if (middle * ((1LL << p) - 1) + middle * (middle - 1) / 2 <= n) {
                low = middle;
            } else {
                high = middle - 1;
            }
        }
        Long q = low;
        if ((q & 1) && q * ((1LL << p) - 1) + q * (q - 1) / 2 == n) {
            result.push_back(q << p);
        }
    }
    if (result.empty()) {
        std::cout << -1 << std::endl;
    } else {
        std::sort(result.begin(), result.end());
        for (Long n : result) {
            std::cout << n << std::endl;
        }
    }
    return 0;
}
