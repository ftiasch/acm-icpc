// POI Stage III -- Shuffle(tas)
#include <cassert>
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 1000001;

int n, k, perm[N];
short selected[N];
bool visit[N], valid[N];
vector <vector <int> > cycles[N];

int gcd(int a, int b) {
    return b == 0? a: gcd(b, a % b);
}

int main() {
    scanf("%d%d", &n, &k);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", perm + i);
    }
    memset(visit, 0, sizeof(visit));
    for (int i = 1; i <= n; ++ i) {
        if (!visit[i]) {
            vector <int> cycle;
            int j = i;
            do {
                visit[j] = true;
                cycle.push_back(j);
                j = perm[j];
            } while (j != i);
            cycles[(int)cycle.size()].push_back(cycle);
        }
    }
    vector <int> divisors;
    for (int d = 1; d * d <= k; ++ d) {
        if (k % d == 0) {
            divisors.push_back(d);
            divisors.push_back(k / d);
        }
    }
    sort(divisors.begin(), divisors.end());
    divisors.erase(unique(divisors.begin(), divisors.end()), divisors.end());
    for (int l = 1; l <= n; ++ l) {
        int m = cycles[l].size();
        if (m == 0) {
            continue;
        }
        vector <int> choices;
        if (gcd(l, k) == 1) {
            choices.push_back(1);
        } else {
            for (int i = 0; i < (int)divisors.size() && divisors[i] <= m; ++ i) {
                if (gcd(l, k / divisors[i]) == 1) {
                    choices.push_back(divisors[i]);
                }
            }
        }
        valid[0] = true;
        for (int i = 1; i <= m; ++ i) {
            valid[i] = false;
            for (int j = 0; j < (int)choices.size() && choices[j] <= i && !valid[i]; ++ j) {
                if (valid[i - choices[j]]) {
                    valid[i] = true;
                    selected[i] = j;
                }
            }
        }
        for (int i = m; i > 0; i -= choices[selected[i]]) {
            int d = choices[selected[i]];
            vector <int> cycle(d * l);
            for (int j = 0; j < d; ++ j) {
                for (int p = 0; p < l; ++ p) {
                    cycle[(j + (long long)p * k) % cycle.size()] = cycles[l].back()[p];
                }
                cycles[l].pop_back();
            }
            for (int j = 0; j < (int)cycle.size(); ++ j) {
                perm[cycle[j]] = cycle[(j + 1) % cycle.size()];
            }
        }
    }
    for (int i = 1; i <= n; ++ i) {
        printf("%d\n", perm[i]);
    }
    return 0;
}
