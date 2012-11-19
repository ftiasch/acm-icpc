// Codeforces Round #149
// Problem E -- XOR on Segment
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 100000;
const int C = 1000;
const int B = 20;

int n, a[N], mask[C], sum[C][B][2], total[B];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    int c = 1;
    while (c * c <= n) {
        c ++;
    }
    memset(sum, 0, sizeof(sum));
    memset(mask, 0, sizeof(mask));
    for (int i = 0; i < n; ++ i) {
        for (int k = 0; k < B; ++ k) {
            sum[i / c][k][a[i] >> k & 1] ++;
        }
    }
    int q;
    scanf("%d", &q);
    while (q --) {
        int t, l, r;
        scanf("%d%d%d", &t, &l, &r);
        l --;
        if (t == 1) {
            memset(total, 0, sizeof(total));
            for (int i = 0; i < c; ++ i) {
                if (l <= i * c && (i + 1) * c <= r) {
                    for (int k = 0; k < B; ++ k) {
                        total[k] += sum[i][k][1];
                    }
                } else {
                    for (int j = max(l, i * c); j < min(r, (i + 1) * c); ++ j) {
                        for (int k = 0; k < B; ++ k) {
                            if ((a[j] ^ mask[i]) >> k & 1) {
                                total[k] ++;
                            }
                        }
                    }
                }
            }
            long long answer = 0;
            for (int k = 0; k < B; ++ k) {
                answer += (long long)total[k] << k;
            }
            cout << answer << endl;
        } else {
            int x;
            scanf("%d", &x);
            for (int i = 0; i < c; ++ i) {
                if (l <= i * c && (i + 1) * c <= r) {
                    mask[i] ^= x;
                    for (int k = 0; k < B; ++ k) {
                        if (x >> k & 1) {
                            swap(sum[i][k][0], sum[i][k][1]);
                        }
                    }
                } else {
                    for (int j = max(l, i * c); j < min(r, (i + 1) * c); ++ j) {
                        for (int k = 0; k < B; ++ k) {
                            if (x >> k & 1) {
                                int t = (a[j] ^ mask[i]) >> k & 1;
                                sum[i][k][t ^ 1] ++;
                                sum[i][k][t] --;
                            }
                        }
                        a[j] ^= x;
                    }
                }
            }
        }
    }
    return 0;
}
