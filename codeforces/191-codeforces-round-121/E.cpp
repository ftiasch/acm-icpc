// Codeforces Round #121
// Problem E -- Thwarting Demonstrations
#include <climits>
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <iostream>
using namespace std;

const int N = 111111;
const long long INF = 200000000000000LL;

int n, treap_count, weight[N], frequency[N], size[N], children[N][2];
long long rank, sum[N], key[N];

void update(int &x) {
    size[x] = size[children[x][0]] + frequency[x] + size[children[x][1]];
}

void rotate(int &x, int t) {
    int y = children[x][t];
    children[x][t] = children[y][t ^ 1];
    children[y][t ^ 1] = x;
    update(x);
    update(y);
    x = y;
}

int my_random() {
    return ((rand() & ((1 << 15) - 1)) << 15) + (rand() & ((1 << 15) - 1));
}

void insert(int &x, long long k) {
    if (x == 0) {
        x = ++ treap_count;
        key[x] = k;
        weight[x] = my_random() ;
        frequency[x] = 1;
        children[x][0] = children[x][1] = 0;
    } else if (key[x] == k) {
        frequency[x] ++;
    } else {
        int t = key[x] < k;
        insert(children[x][t], k);
        if (weight[children[x][t]] < weight[x]) {
            rotate(x, t);
        }
    }
    update(x);
}

int query(int &x, long long k) {
    if (x == 0) {
        return 0;
    }
    if (key[x] < k) {
        return query(children[x][1], k);
    }
    return size[children[x][1]] + frequency[x] + query(children[x][0], k);
}

long long count_pair(long long limit) {
    treap_count = 0;
    weight[0] = INT_MAX;
    size[0] = 0;
    int root = 0;
    insert(root, 0);
    long long result = 0;
    for (int i = 1; i <= n; ++ i) {
        result += query(root, sum[i] - limit);
        insert(root, sum[i]);
    }
    return result;
}

int main() {
    scanf("%d", &n);
    cin >> rank;
    sum[0] = 0;
    for (int i = 0; i < n; ++ i) {
        int a_i;
        scanf("%d", &a_i);
        sum[i + 1] = sum[i] + a_i;
    }
    rank = (long long)n * (n + 1) / 2 - rank + 1;
//cout << count_pair(4) << endl;
    long long lower = -INF;
    long long upper = INF;
    while (lower < upper) {
        long long mider = lower + upper;
        if (mider & 1) {
            mider --;
        }
        mider /= 2;
        if (count_pair(mider) >= rank) {
            upper = mider;
        } else {
            lower = mider + 1;
        }
    }
    cout << upper << endl;
    return 0;
}
