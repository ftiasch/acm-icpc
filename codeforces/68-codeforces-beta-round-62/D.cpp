// Codeforces Beta Round #62
// Problem D -- Half-decay tree
#include <cstdio>
#include <cstring>
#include <map>
#include <algorithm>

using std::max;

std::map <int, int> cost, sum;

double solve(int r, int v) {
    if (sum[r] <= v) {
        return v;
    }
    int left = r << 1;
    int right = left + 1;
    return (solve(left, max(v, cost[r] + sum[right])) + solve(right, max(v, cost[r] + sum[left]))) / 2.0;
}

int main() {
    int n, q;
    scanf("%d%d", &n, &q);
    while (q --) {
        char buffer[6];
        scanf("%s", buffer);
        if (*buffer == 'a') {
            int v, e;
            scanf("%d%d", &v, &e);
            cost[v] += e;
            for (; v; v >>= 1) {
                sum[v] += e;
            }
        } else {
            printf("%.8f\n", solve(1, 0));
        }
    }
    return 0;
}
