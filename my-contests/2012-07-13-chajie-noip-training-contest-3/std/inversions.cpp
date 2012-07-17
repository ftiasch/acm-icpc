#include <vector>
#include <list>
#include <map>
#include <set>
#include <deque>
#include <queue>
#include <stack>
#include <bitset>
#include <algorithm>
#include <functional>
#include <numeric>
#include <utility>
#include <complex>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <cstdlib>
#include <cstring>
#include <ctime>
#include <cassert>
using namespace std;

const int maxn = 111111;

pair<int,int> a[maxn];
int n;

int tree[maxn];

void add(int a, int delta)
{
    for (;a < maxn; a += a & -a)
        tree[a] += delta;
}

int query(int a)
{
    int ret = 0;
    for (; a > 0; a -= a & -a)
        ret += tree[a];
    return ret;
}

long long solve()
{
    sort(a, a + n);
    vector<int> X;
    for (int i = 0; i < n; i++)
        X.push_back(a[i].second);
    sort(X.begin(), X.end());
    X.erase(unique(X.begin(), X.end()), X.end());
    memset(tree, 0, sizeof(tree));
    long long ans = 0;
    for (int i = n - 1; i >= 0; i--) {
        int ai = lower_bound(X.begin(), X.end(), a[i].second) - X.begin();
        ai = ai + 10;
        ans += query(ai - 1);
        add(ai, 1);
    }
    return ans;
}

int main()
{
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        scanf("%d%d", &a[i].first, &a[i].second);
    }
    long long ans = solve();
    for (int i = 0; i < n; i++)
        swap(a[i].first, a[i].second);
    ans = min(ans, solve());
    cout << ans << endl;
}
