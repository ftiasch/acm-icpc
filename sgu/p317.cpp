// SGU 317 -- Fast Ride
#include <cstdio>
#include <cstring>
#include <map>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)
#define ALL(v) (v).begin(), (v).end()

const double INF = 1e9;

int b, n;
std::map <int, std::vector <std::pair <int, int> > > horses;

int main() {
    scanf("%d%d", &b, &n);
    std::vector <int> values;
    values.push_back(0);
    values.push_back(b);
    for (int i = 0; i < n; ++ i) {
        int x, m;
        scanf("%d%d", &x, &m);
        while (m --) {
            int v, d;
            scanf("%d%d", &v, &d);
            horses[x].push_back(std::make_pair(d, v));
        }
        values.push_back(x);
    }
    std::sort(ALL(values));
    values.erase(std::unique(ALL(values)), values.end());
    foreach (iter, horses) {
        std::sort(ALL(iter->second));
    }
    int m = values.size();
    std::vector <double> time(m);
    for (int i = m - 1; i >= 0; -- i) {
        time[i] = values[i] == b ? 0 : INF;
        std::vector <std::pair <int, int> > &h = horses[values[i]];
        int max_v = 0;
        int k = h.size() - 1;
        for (int j = m - 1; j > i; -- j) {
            while (k >= 0 && h[k].first >= values[j] - values[i]) {
                max_v = std::max(max_v, h[k --].second);
            }
            if (max_v > 0) {
                time[i] = std::min(time[i], time[j] + (double)(values[j] - values[i]) / max_v);
            }
        }
    }
    if (time[0] < INF) {
        printf("%.8f\n", time[0]);
    } else {
        puts("-1");
    }
    return 0;
}
