// Codeforces Beta Round #28 
// Problem A -- Bender Problem
#include <cstdio>
#include <cstring>
#include <map>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 500;

int n, m, x[N], y[N], a[N], length[N], attach[N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", x + i, y + i);
    }
    for (int i = 0; i < n; ++ i) {
        a[i] = std::abs(x[(i + 1) % n] - x[i]) + std::abs(y[(i + 1) % n] - y[i]);
    }
    for (int i = 0; i < m; ++ i) {
        scanf("%d", length + i);
    }
    for (int d = 0; d < 2; ++ d) {
        std::map <int, int> count;
        for (int i = 0; i < m; ++ i) {
            count[length[i]] ++;
        }
        for (int i = 0; i < n; i += 2) {
            count[a[(d + i) % n] + a[(d + i + 1) % n]] --;
        }
        bool valid = true;
        foreach (iter, count) {
            valid &= (iter->second) >= 0;
        }
        if (valid) {
            puts("YES");
            std::map <int, std::vector <int> > rods;
            for (int i = 0; i < m; ++ i) {
                rods[length[i]].push_back(i);
            }
            memset(attach, -1, sizeof(attach));
            for (int i = 0; i < n; i += 2) {
                int l = a[(d + i) % n] + a[(d + i + 1) % n];
                attach[(d + i + 1) % n] = rods[l].back();
                rods[l].pop_back();
            }
            for (int i = 0; i < n; ++ i) {
                printf("%d%c", attach[i] == -1 ? -1 : 1 + attach[i], " \n"[i == n - 1]);
            }
            return 0;
        }
    }
    puts("NO");
    return 0;
}
