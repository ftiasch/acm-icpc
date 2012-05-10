// Codeforces Round #119
// Problem D -- BRT Contract
#include <cstdio>
#include <cstring>
#include <climits>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 111111;

int n, green, red, root, treap_count, key[N], value[N], weight[N], children[N][2], lower[N], upper[N], minimum[N];
long long coordinate[N], need[N];

void update(int &x) {
    lower[x] = children[x][0] > 0? lower[children[x][0]]: key[x];
    upper[x] = children[x][1] > 0? upper[children[x][1]]: key[x];
    minimum[x] = min(value[x], min(minimum[children[x][0]], minimum[children[x][1]]));
}

void rotate(int &x, int t) {
    int y = children[x][t];
    children[x][t] = children[y][1 ^ t];
    children[y][1 ^ t] = x;
    update(x);
    update(y);
    x = y;
}

void insert(int &x, int k, int v) {
    if (x == 0) {
        x = treap_count ++;
        key[x] = k;
        value[x] = v;
        weight[x] = rand();
        children[x][0] = children[x][1] = 0;
    } else if (key[x] == k) {
        value[x] = min(value[x], v);
    } else {
        int t = key[x] < k;
        insert(children[x][t], k, v);
        if (weight[children[x][t]] < weight[x]) {
            rotate(x, t);
        }
    }
    update(x);
}

int query(int &x, int l, int r) {
    if (l > r || r < lower[x] || upper[x] < l) {
        return INT_MAX;
    }
    if (l <= lower[x] && upper[x] <= r) {
        return minimum[x];
    }
    int result = min(query(children[x][0], l, r), query(children[x][1], l, r));
    if (l <= key[x] && key[x] <= r) {
        result = min(result, value[x]);
    }
    return result;
}

long long solve(int start_index, int start_time) {
    int mod = green + red;
    // (coordinate[i] - coordinate[start_index] + start_time) % mod >= green
    // (coordinate[i] + delta) % mod >= green
    int delta = (start_time + mod - coordinate[start_index] % mod) % mod;
    int end_index = n + 1;
    end_index = min(end_index, query(root, green - delta, mod - 1 - delta));
    end_index = min(end_index, query(root, green - delta + mod, mod - 1));
    int wait_time = end_index == n + 1? 0: mod - (coordinate[end_index] % mod + delta) % mod;
    return start_time + coordinate[end_index] - coordinate[start_index] + wait_time + need[end_index];
}

int main() {
    scanf("%d%d%d", &n, &green, &red);
    coordinate[0] = 0;
    for (int i = 0; i <= n; ++ i) {
        int length;
        scanf("%d", &length);
        coordinate[i + 1] = coordinate[i] + length;
    }
    need[n + 1] = 0;
    treap_count = 0;
    root = treap_count ++;
    lower[root] = minimum[root] = INT_MAX;
    upper[root] = INT_MIN;
    int mod = green + red;
    for (int i = n; i >= 0; -- i) {
        insert(root, coordinate[i + 1] % mod, i + 1);
        need[i] = solve(i, 0);
    }
    int query_count;
    scanf("%d", &query_count);
    while (query_count > 0) {
        query_count --;
        int start_time;
        scanf("%d", &start_time);
        cout << solve(0, start_time) << endl;
    }
    return 0;
}
