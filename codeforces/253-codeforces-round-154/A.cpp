#include <cstdio>
#include <cstring>

int count[2];

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d%d", count, count + 1);
    int now = count[0] < count[1];
    for (int i = count[0] + count[1]; i; -- i) {
        if (!count[now]) {
            now ^= 1;
        }
        putchar(now ? 'G' : 'B');
        count[now] --;
        now ^= 1;
    }
    puts("");
    return 0;
}
