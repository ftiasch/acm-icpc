// Codeforces Round #151
// Problem C -- Beauty Pageant
#include <cstdio>
#include <cstring>
#include <utility>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 50;

int n, m, a[N];

typedef std::vector <std::pair <int, std::vector <int> > > Vector;

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    m ++;
    Vector sums;
    sums.push_back(std::make_pair(0, std::vector <int>()));
    for (int i = 0; i < n; ++ i) {
        Vector new_sums;
        for (int t = 0; t < 2; ++ t) {
            foreach (iter, sums) {
                new_sums.push_back(*iter);
                if (t) {
                    new_sums.back().first += a[i];
                    new_sums.back().second.push_back(i);
                }
            }
        }
        std::sort(new_sums.begin(), new_sums.end());
        sums.clear();
        foreach (iter, new_sums) {
            if ((int)sums.size() == m) {
                break;
            }
            if (sums.empty() || sums.back().first < iter->first) {
                sums.push_back(*iter);
            }
        }
    }
    foreach (iter, sums) {
        std::vector <int> &v = iter->second;
        if (v.empty()) {
            continue;
        }
        printf("%d", (int)v.size());
        for (int i = 0; i < (int)v.size(); ++ i) {
            printf(" %d", a[v[i]]);
        }
        puts("");
    }
    return 0;
}
