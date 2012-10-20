// Codeforces Round #155
// Problem B -- Reading
#include <cstdio>
#include <cstring>
#include <utility>
#include <functional>
#include <algorithm>
using namespace std;

const int N = 1000;

int n, k, level[N];
pair <int, int> order[N];

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d%d", &n, &k);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", level + i);
        order[i] = make_pair(level[i], i);
    }
    sort(order, order + n, greater <pair <int, int> > ());
    printf("%d\n", order[k - 1].first);
    for (int i = 0; i < k; ++ i) {
        printf("%d%c", order[i].second + 1, i == k - 1 ? '\n' : ' ');
    }
    return 0;
}
