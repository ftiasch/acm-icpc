// SGU 224 -- Little Queens
#include <cstdio>
#include <cstring>

int n, k, all;

int count(int i, int left, int column, int diagonal, int diagonal_2) {
    if (n - i < left) {
        return 0;
    }
    if (i == n) {
        return 1;
    }
    int ret = count(i + 1, left, column, diagonal << 1, diagonal_2 >> 1);
    if (left) {
        int valid = ~column & ~diagonal & ~diagonal_2 & all;
        while (valid) {
            int x = valid & -valid;
            ret += count(i + 1, left - 1, column | x, (diagonal | x) << 1, (diagonal_2 | x) >> 1);
            valid ^= x;
        }
    }
    return ret;
}

int main() {
    scanf("%d%d", &n, &k);
    all = (1 << n) - 1;
    printf("%d\n", count(0, k, 0, 0, 0));
    return 0;
}
