//VK Cup 2012 Round 1
// Problem C - Abracadabra
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

typedef long long LL;

LL result;

void solve(LL n, LL l_1, LL r_1, LL l_2, LL r_2) {
    if (r_1 - l_1 >= result && r_2 - l_2 >= result) {
        result = max(min(r_1, r_2) - max(l_1, l_2) + 1, result);
        if (n > 1) {
            LL m = 1LL << (n - 1);
            if (l_1 < m && l_2 < m) {
                solve(n - 1, l_1, min(r_1, m - 1), l_2, min(r_2, m - 1));
            }
            if (l_1 < m && r_2 > m) {
                solve(n - 1, l_1, min(r_1, m - 1), max(l_2 - m, 1LL), r_2 - m);
            }
            if (r_1 > m && l_2 < m) {
                solve(n - 1, max(l_1 - m, 1LL), r_1 - m, l_2, min(r_2, m - 1));
            }
            if (r_1 > m && r_2 > m) {
                solve(n - 1, max(l_1 - m, 1LL), r_1 - m, max(l_2 - m, 1LL), r_2 - m);
            }
        }
    }
}

int main() {
    LL l_1, r_1, l_2, r_2;
    cin >> l_1 >> r_1 >> l_2 >> r_2;
    result = 0;
    solve(36, l_1, r_1, l_2, r_2);
    cout << result << endl;
    return 0;
}
