// SGU 361 -- National Flag
#include <cstdio>
#include <cstring>
#include <cassert>

const int N = 200;

int n, m;

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            putchar(i % 3 + j % 3 == 2 ? '#' : '0');
        }
        puts("");
    }
}
