// Codeforces Round #133
// Problem E -- Martian Luck
#include <cstdio>
#include <cstring>
#include <map>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 100000;

int k, b, n, a[N];
long long sum[N + 1];

int main() {
    scanf("%d%d%d", &k, &b, &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    long long result = 0;
    map <long long, int> counter, modCounter;
    sum[n] = 0;
    counter[0] ++;
    modCounter[0] ++;
    long long zero = 0;
    for (int i = n - 1; i >= 0; -- i) {
        sum[i] = sum[i + 1] + a[i];
        zero += counter[sum[i]];
        result += modCounter[(sum[i] + (k - 1) - b) % (k - 1)];
        counter[sum[i]] ++;
        modCounter[sum[i] % (k - 1)] ++;
    }
    if (b == 0) {
        cout << zero << endl;
    } else {
        if (b == k - 1) {
            result -= zero;
        }
        cout << result << endl;
    }
    return 0;
}
