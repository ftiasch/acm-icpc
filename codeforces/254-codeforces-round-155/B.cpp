// Codeforces Round #155
// Problem B -- Jury Size
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <algorithm>

using namespace std;

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int DAYS[13] = {-1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

int get_index(int m, int d) {
    int ret = d;
    for (int i = 1; i < m; ++ i) {
        ret += DAYS[i];
    }
    return ret;
}

int n;

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d", &n);
    vector <pair <int, int> > events;
    for (int i = 0; i < n; ++ i) {
        int m, d, p, t;
        scanf("%d%d%d%d", &m, &d, &p, &t);
        int start = get_index(m, d);
        events.push_back(make_pair(start - t, p));
        events.push_back(make_pair(start, -p));
    }
    sort(events.begin(), events.end());
    int answer = 0;
    int total = 0;
    foreach (iter, events) {
        total += iter->second;
        answer = max(answer, total);
    }
    printf("%d\n", answer);
    return 0;
}
