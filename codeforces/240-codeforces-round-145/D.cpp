#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 100000;

int n, m, a[N], b[N];

typedef vector <int> Vector;
typedef pair <int, pair <Vector, Vector> > Pair;

Pair solve(int init) {
    int step = 0;
    vector <int> order, turn;
    int now = init;
    for (int i = 0, j = 0; i < n || j < m; now ^= 1) {
        bool found = false;
        while (i < n && a[i] == now) {
            found = true;
            order.push_back(i);
            i ++;
        }
        while (j < m && b[j] == now) {
            found = true;
            order.push_back(n + j);
            j ++;
        }
        if (!found) {
            return make_pair(INT_MAX, make_pair(order, turn));
        }
        step ++;
        turn.push_back((int)order.size());
    }
    if (now) {
        step --;
        turn.pop_back();
    }
    return make_pair(step, make_pair(order, turn));
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    scanf("%d", &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d", b + i);
    }
    Pair result(INT_MAX, make_pair(vector <int>(), vector <int>()));
    for (int init = 0; init < 2; ++ init) {
        Pair ret = solve(init);
        result = min(result, ret);
    }
    for (int i = 0; i < n + m; ++ i) {
        printf("%d%c", result.second.first[i] + 1, i == n + m - 1 ? '\n' : ' ');
    }
    printf("%d\n", result.first);
    Vector &ret = result.second.second;
    for (int i = 0; i < (int)ret.size(); ++ i) {
        printf("%d%c", ret[i], i == (int)ret.size() - 1 ? '\n' : ' ');
    }
    return 0;
}
