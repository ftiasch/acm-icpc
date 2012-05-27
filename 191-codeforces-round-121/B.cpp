// Codeforces Round #121
// Problem B -- Demonstration
#include <cstdio>
#include <cstring>
#include <vector>
#include <functional>
#include <utility>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 111111;

int n, m, a[N], rank[N];
long long b, sum[N];
//vector <int> v;
vector <pair <int, int> > v;

int main() {
    scanf("%d%d", &n, &m);
    cin >> b;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    v.resize(n - 1);
    for (int i = 0; i < n - 1; ++ i) {
        //v[i] = a[i];
        v[i] = make_pair(a[i], i);
    }
    sort(v.begin(), v.end(), greater <pair <int, int> >());
    sum[0] = 0;
    for (int i = 0; i < n - 1; ++ i) {
        rank[v[i].second] = i;
        sum[i + 1] = sum[i] + v[i].first;
    }
    int result = 0;
    while (result < n - 1) {
        long long cost = sum[m - 1];
        if (rank[result] < m - 1) {
            cost = cost - a[result] + v[m - 1].first;
        }
        if (b - cost < a[result]){
            break;
        }
        result ++;
    }
    printf("%d\n", result + 1);
    return 0;
}
