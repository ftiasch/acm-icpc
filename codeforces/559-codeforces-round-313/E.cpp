#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

const int N = 100 + 2;

int maximum[N][N];

void update(int &x, int a)
{
    x = std::max(x, a);
}

int main()
{
    int n;
    scanf("%d", &n);
    std::vector <std::pair <int, int>> lights;
    for (int i = 0; i < n; ++ i) {
        int a, l;
        scanf("%d%d", &a, &l);
        lights.push_back({a, l});
    }
    std::sort(ALL(lights));
    std::vector <int> a, l;
    a.push_back(-1e8);
    l.push_back(0);
    for (const auto &it : lights) {
        a.push_back(it.first);
        l.push_back(it.second);
    }
    a.push_back(2e8);
    l.push_back(0);
    memset(maximum, -1, sizeof(maximum));
    maximum[0][0] = 0;
    for (int i = 0; i < n + 2; ++ i) {
        for (int j = 0; j <= i; ++ j) {
            if (~maximum[i][j]) {
                std::vector <std::pair <int, int>> stack;
                std::vector <int> sum;
                stack.push_back({a[j], j});
                sum.push_back(std::max(a[j] + l[j] - a[i], 0));
                for (int k = i + 1; k < n + 2; ++ k) {
                    int length = maximum[i][j];
                    if (a[k] - l[k] <= a[i]) {
                        length += a[k] - a[i];
                    } else {
                        int p = (int)(std::lower_bound(ALL(stack), std::make_pair(a[k] - l[k], INT_MIN)) - stack.begin()) - 1;
                        length += sum[p];
                        int t = stack[p].second;
                        if (a[t] + l[t] >= a[k]) {
                            length -= a[t] + l[t] - a[k];
                        }
                        length += a[k] - std::max(a[t] + l[t], a[k] - l[k]);
                    }
                    update(maximum[k][stack.back().second], length);
                    int t = stack.back().second;
                    if (a[k] + l[k] > a[t] + l[t]) {
                        stack.push_back({a[k], k});
                        sum.push_back(sum.back() + a[k] + l[k] - std::max(a[t] + l[t], a[k]));
                    }
                }
            }
        }
    }
    int result = -1;
    for (int j = 0; j < n + 2; ++ j) {
        update(result, maximum[n + 1][j]);
    }
    printf("%d\n", result);
    return 0;
}
