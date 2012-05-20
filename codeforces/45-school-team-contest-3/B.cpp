// School Team Contest #3
// Problem B -- School
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100001;

int n, m, people[N], next[N], step[N], rating[N], delta[N], result[N];

int find(int i) {
    if (next[i] != i) {
        find(next[i]);
        step[i] += step[next[i]];
        next[i] = find(next[i]);
    }
    return next[i];
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; ++ i) {
        scanf("%d", people + i);
        next[i] = i;
        step[i] = 0;
    }
    next[0] = 0;
    for (int i = 1; i <= m; ++ i) {
        scanf("%d", delta + i);
    }
    for (int i = 1; i <= m; ++ i) {
        scanf("%d", rating + i);
    }
    result[0] = 0;
    for (int i = 1; i <= m; ++ i) {
        int left = rating[i];
        int p = (delta[i] + result[i - 1] - 1) % n + 1;
        while (left >= 0) {
            if (find(p) == 0) {
                break;
            }
            if (step[p] < left) {
                result[i] ++;
                left -= step[p];
                p = find(p);
                if (find(people[p]) == find(p)) {
                    next[p] = 0;
                    break;
                }
                next[p] = people[p];
                step[p] = 1;
            } else {
                break;
            }
        }
    }
    for (int i = 1; i <= m; ++ i) {
        printf("%d\n", result[i]);
    }
    return 0;
}
