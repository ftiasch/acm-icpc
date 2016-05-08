#include <algorithm>
#include <cstdio>
#include <cstring>
#include <functional>
#include <utility>
#include <vector>

int main()
{
    int n;
    scanf("%d", &n);
    std::vector<int> c(n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", &c[i]);
        c[i] --;
    }
    std::vector<int> result(n);
    for (int i = 0; i < n; ++ i) {
        std::vector<int> count(n);
        std::pair<int, int> best(0, 0);
        for (int j = i; j < n; ++ j) {
            count[c[j]] ++;
            best = std::max(best, {count[c[j]], -c[j]});
            result[-best.second] ++;
        }
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", result[i], " \n"[i == n - 1]);
    }
}
