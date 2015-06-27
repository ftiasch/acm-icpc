#include <algorithm>
#include <cstdio>

int main()
{
    int n, k;
    scanf("%d%d", &n, &k);
    int l = 0;
    for (int i = 0; i < k; ++ i) {
        int m;
        scanf("%d", &m);
        int c = 0;
        while (m --) {
            int a;
            scanf("%d", &a);
            if (a == c + 1) {
                l = std::max(l, ++ c);
            } else {
                c = 0;
            }
        }
    }
    printf("%d\n", (n - l << 1) - k + 1);
    return 0;
}
