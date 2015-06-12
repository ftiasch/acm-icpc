#include <algorithm>
#include <cstdio>
#include <iostream>
#include <map>
#include <vector>

const int N = 300000;

int a[N + 1], s[N + 1], pre[N + 2], nxt[N + 2];
std::pair <int, int> order[N];
std::vector <int> values[1000000];

int count(int v, int l, int r) {
    const std::vector <int> &vs = values[v];
    return std::lower_bound(vs.begin(), vs.end(), r) - std::lower_bound(vs.begin(), vs.end(), l);
}

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", a + i);
    }
    s[0] = 0;
    for (int i = 1; i <= n; ++ i) {
        s[i] = (s[i - 1] + a[i]) % m;
    }
    for (int i = 0; i <= n; ++ i) {
        values[s[i]].push_back(i);
    }
    for (int i = 1; i <= n; ++ i) {
        order[i - 1] = {a[i], i};
    }
    std::sort(order, order + n);
    for (int i = 1; i <= n; ++ i) {
        pre[i] = i - 1;
        nxt[i] = i + 1;
    }
    long long result = -n;
    for (int _ = 0; _ < n; ++ _) {
        int i = order[_].second;
        if (i - pre[i] <= nxt[i] - i) {
            for (int j = pre[i]; j < i; ++ j) {
                result += count((s[j] + a[i]) % m, i, nxt[i]);
            }
        } else {
            for (int j = i; j < nxt[i]; ++ j) {
                result += count((s[j] + m - a[i] % m) % m, pre[i], i);
            }
        }
        nxt[pre[i]] = nxt[i];
        pre[nxt[i]] = pre[i];
    }
    std::cout << result << std::endl;
    return 0;
}
