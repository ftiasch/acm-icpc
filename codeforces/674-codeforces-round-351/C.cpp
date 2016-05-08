#include <algorithm>
#include <cstdio>
#include <cstring>
#include <deque>
#include <functional>
#include <utility>
#include <vector>

struct Line
{
    Line(double k, double b) : k(k), b(b) {}

    double at(double x)
    {
        return k * x + b;
    }

    double k, b;
};

bool check(const Line& u, const Line& v, const Line& w)
{
    // u.k > v.k > w.k
    return (v.b - u.b) / (u.k - v.k) < (w.b - v.b) / (v.k - w.k);
}

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    std::vector<int> t(n + 1);
    std::vector<long long> s(n + 1);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", &t[i]);
    }
    for (int i = 1; i <= n; ++ i) {
        s[i] = s[i - 1] + t[i];
    }
    std::vector<double> qsum(n + 1), rsum(n + 1);
    for (int i = 1; i <= n; ++ i) {
        qsum[i] = qsum[i - 1] + (double)s[i] / t[i];
        rsum[i] = rsum[i - 1] + 1. / t[i];
    }
    // dp[i] = dp'[j] + (qsum[i] - qsum[j]) - s[j] * (rsum[i] - rsum[j])
    // dp[i] = dp'[j] - qsum[j] + s[j] * rsum[j] - s[j] * (rsum[i]) + qsum[i]
    std::vector<double> dp(n + 1);
    for (int i = 1; i <= n; ++ i) {
        dp[i] = -1;
    }
    for (int _ = 0; _ < m; ++ _) {
        std::vector<double> new_dp(n + 1);
        std::deque<Line> lines;
        for (int i = 0; i <= n; ++ i) {
            if (lines.empty()) {
                new_dp[i] = -1;
            } else {
                auto x = rsum[i];
                while ((int)lines.size() >= 2 && lines[0].at(x) > lines[1].at(x)) {
                    lines.pop_front();
                }
                new_dp[i] = lines[0].at(rsum[i]) + qsum[i];
            }
            if (dp[i] > -.5) {
                Line l(-s[i], dp[i] - qsum[i] + s[i] * rsum[i]);
                while ((int)lines.size() >= 2 && !check(lines[(int)lines.size() - 2], lines.back(), l)) {
                    lines.pop_back();
                }
                lines.push_back(l);
            }
        }
        dp.swap(new_dp);
    }
    printf("%.9f\n", dp[n]);
}
