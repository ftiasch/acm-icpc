#include <cassert>
#include <cstdio>
#include <cstring>

const int N = 400;
const int M = 200000;
const int MM = 50;

int aa[N], bb[N];
unsigned long long valid[M + 1];
short first[M + 1][MM + 1];

int main()
{
    int test_count;
    scanf("%d", &test_count);
    while (test_count --) {
        int n, q;
        scanf("%d%d", &n, &q);
        memset(valid, 0, sizeof(valid));
        memset(first, -1, sizeof(first));
        valid[0] = 1ULL;
        for (int i = 0; i < n; ++ i) {
            int a, b;
            scanf("%d%d", &b, &a);
            aa[i] = a;
            bb[i] = b;
            for (int j = M; j >= a; -- j) {
                unsigned long long backup = valid[j];
                valid[j] |= valid[j - a] << b & (1ULL << MM + 1) - 1;
                for (unsigned long long bit = backup ^ valid[j]; bit; bit = bit - 1 & bit) {
                    first[j][__builtin_ctzll(bit)] = i;
                }
            }
        }
        while (q --) {
            int b, a;
            scanf("%d%d", &b, &a);
            if (valid[a] >> b & 1) {
                bool isfirst = true;
                while (a && b) {
                    int i = first[a][b];
                    assert(~i);
                    if (isfirst) {
                        isfirst = false;
                    } else {
                        putchar(' ');
                    }
                    printf("%d", i + 1);
                    a -= aa[i];
                    b -= bb[i];
                }
                assert(!a && !b);
                puts("");
            } else {
                puts("No solution!");
            }
        }
    }
    return 0;
}
