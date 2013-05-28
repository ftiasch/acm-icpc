// Codeforces Beta Round #28 
// Problem D -- Don't fear, DravDe is kind
#include <cstdio>
#include <cstring>
#include <climits>
#include <map>
#include <vector>
#include <utility>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;

int n, v[N], c[N], l[N], r[N];
std::map <int, std::vector <int> > sums;

int maximum[N], from[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d%d%d", v + i, c + i, l + i, r + i);
        sums[l[i] + c[i] + r[i]].push_back(i);
    }
    std::fill(maximum, maximum + n, INT_MIN);
    foreach (viter, sums) {
        std::vector <int> &ids = viter->second;
        std::map <int, std::pair <int, int> > map;
        foreach (iter, ids) {
            int i = *iter;
            if (l[i] == 0) {
                maximum[i] = v[i];
                from[i] = -1;
            } else if (map.count(l[i])) {
                maximum[i] = map[l[i]].first + v[i];
                from[i] = map[l[i]].second;
            }
            if (maximum[i] != INT_MIN) {
                map[l[i] + c[i]] = std::max(map[l[i] + c[i]], std::make_pair(maximum[i], i));
            }
        }
    }
    int answer = INT_MIN;
    for (int i = 0; i < n; ++ i) {
        if (r[i] == 0) {
            answer = std::max(answer, maximum[i]);
        }
    }
    if (answer == INT_MIN) {
        puts("0");
    } else {
        int end = 0;
        for (int i = 0; i < n; ++ i) {
            if (r[i] == 0 && maximum[i] == answer) {
                end = i;
            }
        }
        std::vector <int> choices;
        for (int i = end; i != -1; i = from[i]) {
            choices.push_back(i);
        }
        std::reverse(choices.begin(), choices.end());
        printf("%d\n", (int)choices.size());
        for (int i = 0; i < (int)choices.size(); ++ i) {
            printf("%d%c", choices[i] + 1, " \n"[i == (int)choices.size() - 1]);
        }
    }
    return 0;
}
