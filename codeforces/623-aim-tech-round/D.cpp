#include <cstdio>
#include <queue>
#include <utility>
#include <vector>

int main()
{
    int n;
    scanf("%d", &n);
    std::vector<double> q(n);
    for (int i = 0; i < n; ++ i) {
        int p;
        scanf("%d", &p);
        q[i] = 1. - p / 100.;
    }
    if (n == 1) {
        puts("1");
    } else {
        std::vector<double> pw(n);
        for (int i = 0; i < n; ++ i) {
            pw[i] = q[i];
        }
        std::priority_queue<std::pair<double, int>> h;
        for (int i = 0; i < n; ++ i) {
            h.push({(1. - pw[i] * q[i]) / (1 - pw[i]), i});
        }
        double pd = 1.;
        for (int i = 0; i < n; ++ i) {
            pd *= 1 - pw[i];
        }
        double result = n + 1 - pd;
        while (1 - pd > 1e-12) {
            std::pair<double, int> t = h.top();
            h.pop();
            pd *= t.first;
            result += 1 - pd;
            int i = t.second;
            pw[i] *= q[i];
            h.push({(1. - pw[i] * q[i]) / (1 - pw[i]), i});
        }
        printf("%.10f\n", result);
    }
    return 0;
}
