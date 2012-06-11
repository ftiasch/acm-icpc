// SGU 131 -- Hardwoord floor
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

int n, m;

int main() {
    scanf("%d%d", &n, &m);
    long long *current = new long long[1 << (m + 1)];
    long long *previous = new long long[1 << (m + 1)];
    for (int mask = 0; mask < (1 << (m + 1)); ++ mask) {
        current[mask] = 0;
    }
    current[0] = 1;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            swap(current, previous);
            for (int mask = 0; mask < (1 << (m + 1)); ++ mask) {
                current[mask] = 0;
            }
            for (int mask_iter = 0; mask_iter < (1 << (m + 1)); ++ mask_iter) {
                long long value = previous[mask_iter];
                int mask = j == 0? (mask_iter << 1): mask_iter;
                if (mask >= (1 << (m + 1))) {
                    continue;
                }
                bool left = j > 0 && (mask >> (j - 1) & 1) == 1;
                bool up = (mask >> (j + 1) & 1) == 1;
                bool corner = (mask >> j & 1) == 1;
                mask &= ~(1 << j);
                if (!corner) {
                    current[mask | (1 << j)] += value;
                }
                if (left) {
                    current[mask & ~(1 << (j - 1))] += value;
                }
                if (up) {
                    current[mask & ~(1 << (j + 1))] += value;
                }
                if (!corner && left && up) {
                    current[mask & ~(1 << (j - 1)) & ~(1 << (j + 1))] += value;
                }
                if (!corner && up && (mask >> (j + 2) & 1) == 1) {
                    current[mask & ~(1 << (j + 1)) & ~(1 << (j + 2))] += value;
                }
            }
        }
    }
    cout << current[0] << endl;
    return 0;
}
