#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <set>
#include <utility>
#include <algorithm>

#define ALL(v) (v).begin(), (v).end()

const int N = 200000;

int n, x[N], y[N];
char type[N];

std::set < std::pair <int, int> > sets[N];

int main() {
    scanf("%d", &n);
    std::vector <int> values;
    for (int i = 0; i < n; ++ i) {
        char buffer[7];
        scanf("%s%d%d", buffer, x + i, y + i);
        type[i] = *buffer;
        values.push_back(y[i]);
    }
    std::sort(ALL(values));
    values.erase(std::unique(ALL(values)), values.end());
    for (int i = 0; i < n; ++ i) {
        std::pair <int, int> p(x[i], y[i]);
        if (type[i] == 'f') {
            std::pair <int, int> q(INT_MAX, INT_MAX);
            for (int k = std::upper_bound(ALL(values), y[i]) - values.begin(); k < (int)values.size(); k += ~k & k + 1) {
                std::set < std::pair <int, int> > :: iterator iter = sets[k].upper_bound(std::make_pair(x[i], INT_MAX));
                if (iter != sets[k].end()) {
                    q = std::min(q, *iter);
                }
            }
            if (q.first == INT_MAX) {
                puts("-1");
            } else {
                printf("%d %d\n", q.first, q.second);
            }
        } else {
            for (int k = std::lower_bound(ALL(values), y[i]) - values.begin(); k >= 0; k -= ~k & k + 1) {
                if (type[i] == 'a') {
                    sets[k].insert(p);
                } else {
                    sets[k].erase(p);
                }
            }
        }
    }
    return 0;
}
