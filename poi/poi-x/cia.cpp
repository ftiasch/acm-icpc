// POI X Stage I -- Sequences without Stammers(cia)
#include <cstdio>
using namespace std;

const int N = 10000001;

int n;
bool cnt[N];

int main() {
    scanf("%d", &n);
    if (n <= 3) {
        printf("%d\n", n == 1? 1: 2);
        for (int i = 0; i < n; ++ i) {
            putchar('a' + (i & 1));
        }
    } else {
        puts("3");
        cnt[0] = 0;
        for (int i = 1; i <= n; ++ i) {
            cnt[i] = cnt[i >> 1] ^ (i & 1);
        }
        for (int i = 1; i <= n; ++ i) {
            if (cnt[i - 1] == cnt[i]) {
                putchar('c');
            } else {
                putchar(cnt[i]? 'a': 'b');
            }
        }
    }
    puts("");
    return 0;
}
