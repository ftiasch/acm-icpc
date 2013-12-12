#include <cstdio>
#include <cstring>
#include <iostream>

const int N = 100000;
const int C = 20;

char string[N + 1];
int count[1 << C];

int main() {
    int test_count;
    scanf("%d", &test_count);
    while (test_count --) {
        scanf("%s", string);
        memset(count, 0, sizeof(count));
        count[0] ++;
        for (int i = 0, type = 0; string[i]; ++ i) {
            count[type ^= 1 << string[i] - 'a'] ++;
        }
        for (int i = 0; i < C; ++ i) {
            for (int mask = (1 << C) - 1; mask >= 0; -- mask) {
                if (mask >> i & 1) {
                    count[mask ^ 1 << i] += count[mask];
                }
            }
        }
//for (int mask = 0; mask < 1 << C; ++ mask) {
//    for (int i = 0; i < C; ++ i) {
//        printf("%d", mask >> i & 1);
//    }
//    printf(": %d\n", count[mask]);
//}
        int q;
        scanf("%d", &q);
        while (q --) {
            int k;
            scanf("%d", &k);
            int all = 0;
            while (k --) {
                static char buffer[2];
                scanf("%s", buffer);
                all |= 1 << *buffer - 'a';
            }
            long long result = 0;
            for (int mask = all; ; mask = mask - 1 & all) {
                int c = 0;
                for (int super = all ^ mask; ; super = super - 1 & (all ^ mask)) {
                    int signum = __builtin_parity(super) ? -1 : 1;
                    c += signum * count[super | mask];
                    if (!super) {
                        break;
                    }
                }
                result += (long long)c * (c - 1) / 2;
                if (!mask) {
                    break;
                }
            }
            std::cout << result << std::endl;
        }
    }
    return 0;
}
