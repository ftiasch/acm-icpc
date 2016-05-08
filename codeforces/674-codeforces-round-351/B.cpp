#include <algorithm>
#include <cstdio>
#include <cstring>
#include <functional>
#include <utility>
#include <vector>

int main()
{
    int n, m, a, b, c, d;
    scanf("%d%d%d%d%d%d", &n, &m, &a, &b, &c, &d);
    a --;
    b --;
    c --;
    d --;
    if (n == 4 || m < n + 1) {
        puts("-1");
    } else {
        std::vector<int> order;
        order.push_back(a);
        order.push_back(c);
        for (int i = 0; i < n; ++ i) {
            if (i != a && i != b && i != c && i != d) {
                order.push_back(i);
            }
        }
        order.push_back(d);
        order.push_back(b);
        for (int i = 0; i < n; ++ i) {
            printf("%d%c", order[i] + 1, " \n"[i == n - 1]);
        }
        printf("%d %d ", c + 1, a + 1);
        for (int i = 2; i < n - 2; ++ i) {
            printf("%d ", order[i] + 1);
        }
        printf("%d %d\n", b + 1, d + 1);
    }
}
