#include <cstdio>
#include <cstring>
using namespace std;

int n, p, ways[10000001];

int main() {
    scanf("%d%d", &n, &p);
    memset(ways, 0, sizeof(ways));
    ways[2] = 1 % p;
    for (int i = 3; i <= n; ++ i) {
        ways[i] = ways[i - 1];
        if ((i & 1) == 0) {
            ways[i] += ways[i >> 1];
            ways[i] %= p;
        }
    }
    printf("%d\n", ways[n]);
    return 0;
}
