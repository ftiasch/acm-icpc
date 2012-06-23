// SGU 180 -- Inversions
#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 111111;

int n, a[N], b[N];

int main() {
    scanf("%d", &n);
    vector <int> v;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        v.push_back(a[i]);
    }
    sort(v.begin(), v.end());
    v.erase(unique(v.begin(), v.end()), v.end());
    for (int i = 0; i < n; ++ i) {
        a[i] = lower_bound(v.begin(), v.end(), a[i]) - v.begin() + 1;
    }
    long long result = 0;
    memset(b, 0, sizeof(b));
    for (int i = n - 1; i >= 0; -- i) {
        for (int j = a[i] - 1; j >= 1; j -= j & -j) {
            result += b[j];
        }
        for (int j = a[i]; j <= n; j += j & -j) {
            b[j] ++;
        }
    }
    cout << result << endl;
    return 0;
}
