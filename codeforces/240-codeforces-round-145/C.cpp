// Codeforces Round #145
// Problem C -- Practice
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

int n;
vector <vector <int> > events;

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i) 

void solve(int l, int r, int depth) {
    if (r - l > 1) {
        if ((int)events.size() < depth) {
            events.push_back(vector <int>());
        }
        int m = (l + r) >> 1;
        for (int i = l; i < m; ++ i) {
            events[depth - 1].push_back(i);
        }
        solve(l, m, depth + 1);
        solve(m, r, depth + 1);
    }
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d", &n);
    solve(0, n, 1);
    printf("%d\n", (int)events.size());
    foreach (event, events) {
        printf("%d", event->size());
        foreach (iter, *event) {
            printf(" %d", *iter + 1);
        }
        puts("");
    }
    return 0;
}
